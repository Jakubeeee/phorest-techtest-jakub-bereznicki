package com.jakubeeee.product;

import com.jakubeeee.appointment.AppointmentEntity;
import com.jakubeeee.appointment.AppointmentService;
import com.jakubeeee.misc.CsvReader;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import static com.jakubeeee.misc.CustomCollectors.toSingleton;
import static com.jakubeeee.product.ProductCsvMapper.mapPurchase;
import static com.jakubeeee.product.ProductCsvMapper.mapService;
import static com.jakubeeee.product.ProductDiscriminator.PURCHASE;
import static com.jakubeeee.product.ProductDiscriminator.SERVICE;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final AppointmentService appointmentService;

    @Transactional
    public void bulkSaveServices(@NonNull InputStream data) {
        bulkSaveProducts(data, SERVICE);
    }

    @Transactional
    public void bulkSavePurchases(@NonNull InputStream data) {
        bulkSaveProducts(data, PURCHASE);
    }

    private void bulkSaveProducts(InputStream data, ProductDiscriminator type) {
        var csvRecords = readCsv(data);
        var appointmentIdentifiers = extractAppointmentIdentifiers(csvRecords);
        var appointments = appointmentService.fetch(appointmentIdentifiers);
        var services = mapEntities(csvRecords, appointments, type);
        repository.saveAll(services);
    }

    private List<ProductCsvRecord> readCsv(InputStream data) {
        return CsvReader.read(data, ProductCsvRecord.class)
                .stream()
                .toList();
    }

    private List<String> extractAppointmentIdentifiers(List<ProductCsvRecord> csvRecords) {
        return csvRecords.stream()
                .map(ProductCsvRecord::getAppointmentIdentifier)
                .distinct()
                .toList();
    }

    private List<ProductEntity> mapEntities(List<ProductCsvRecord> csvRecords,
                                            List<AppointmentEntity> appointments,
                                            ProductDiscriminator type) {
        return csvRecords.stream()
                .map(csvRecord -> mapSingleEntity(csvRecord, determineAppointment(csvRecord, appointments), type))
                .toList();
    }

    private ProductEntity mapSingleEntity(ProductCsvRecord csvRecord,
                                          AppointmentEntity appointment,
                                          ProductDiscriminator type) {
        return switch (type) {
            case SERVICE -> mapService(csvRecord, appointment);
            case PURCHASE -> mapPurchase(csvRecord, appointment);
        };
    }

    private AppointmentEntity determineAppointment(ProductCsvRecord csvRecord, List<AppointmentEntity> appointments) {
        var appointmentIdentifier = csvRecord.getAppointmentIdentifier();
        return appointments.stream()
                .filter(appointment -> Objects.equals(appointment.getIdentifier(), appointmentIdentifier))
                .collect(toSingleton(
                        noAppointmentFoundMessage(appointmentIdentifier),
                        multipleAppointmentsFoundMessage(appointmentIdentifier)
                ));
    }

    private String noAppointmentFoundMessage(String appointmentIdentifier) {
        return "No appointment found for identifier: <%s>".formatted(appointmentIdentifier);
    }

    private String multipleAppointmentsFoundMessage(String appointmentIdentifier) {
        return "Multiple appointments found for identifier: <%s>".formatted(appointmentIdentifier);
    }
}
