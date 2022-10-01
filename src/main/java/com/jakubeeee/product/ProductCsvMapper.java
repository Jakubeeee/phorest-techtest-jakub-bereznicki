package com.jakubeeee.product;

import com.jakubeeee.appointment.AppointmentEntity;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
final class ProductCsvMapper {

    static ProductEntity mapService(@NonNull ProductCsvRecord csvRecord, @NonNull AppointmentEntity appointment) {
        return new ServiceEntity(
                csvRecord.getIdentifier(),
                appointment,
                csvRecord.getName(),
                csvRecord.getPrice(),
                csvRecord.getLoyaltyPoints());
    }

    static ProductEntity mapPurchase(@NonNull ProductCsvRecord csvRecord, @NonNull AppointmentEntity appointment) {
        return new PurchaseEntity(
                csvRecord.getIdentifier(),
                appointment,
                csvRecord.getName(),
                csvRecord.getPrice(),
                csvRecord.getLoyaltyPoints());
    }
}
