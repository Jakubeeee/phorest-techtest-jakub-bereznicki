package com.jakubeeee.appointment;

import com.jakubeeee.client.ClientEntity;
import com.jakubeeee.client.ClientService;
import com.jakubeeee.misc.CsvReader;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import static com.jakubeeee.appointment.AppointmentCsvMapper.map;
import static com.jakubeeee.misc.CustomCollectors.toSingleton;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository repository;
    private final ClientService clientService;

    /**
     * Saves multiple {@link AppointmentEntity} data by parsing provided CSV file.
     *
     * @param data the CSV file
     */
    @Transactional
    public void bulkSave(@NonNull InputStream data) {
        var csvRecords = readCsv(data);
        var clientIdentifiers = extractClientIdentifiers(csvRecords);
        var clients = clientService.fetch(clientIdentifiers);
        var appointments = mapEntities(csvRecords, clients);
        log.info("Bulk saving <%s> appointments".formatted(clients.size()));
        repository.saveAll(appointments);
    }

    public List<AppointmentEntity> fetch(@NonNull List<String> identifiers) {
        return repository.findAllByIdentifierIn(identifiers);
    }

    private List<AppointmentCsvRecord> readCsv(InputStream data) {
        return CsvReader.read(data, AppointmentCsvRecord.class)
                .stream()
                .toList();
    }

    private List<String> extractClientIdentifiers(List<AppointmentCsvRecord> csvRecords) {
        return csvRecords.stream()
                .map(AppointmentCsvRecord::getClientIdentifier)
                .distinct()
                .toList();
    }

    private List<AppointmentEntity> mapEntities(List<AppointmentCsvRecord> csvRecords, List<ClientEntity> clients) {
        return csvRecords.stream()
                .map(csvRecord -> map(csvRecord, determineClient(csvRecord, clients)))
                .toList();
    }

    private ClientEntity determineClient(AppointmentCsvRecord csvRecord, List<ClientEntity> clients) {
        var clientIdentifier = csvRecord.getClientIdentifier();
        return clients.stream()
                .filter(client -> Objects.equals(client.getIdentifier(), clientIdentifier))
                .collect(toSingleton(
                        noClientFoundMessage(clientIdentifier),
                        multipleClientsFoundMessage(clientIdentifier)
                ));
    }

    private String noClientFoundMessage(String clientIdentifier) {
        return "No client found for identifier: <%s>".formatted(clientIdentifier);
    }

    private String multipleClientsFoundMessage(String clientIdentifier) {
        return "Multiple clients found for identifier: <%s>".formatted(clientIdentifier);
    }
}
