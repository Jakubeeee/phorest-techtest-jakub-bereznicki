package com.jakubeeee.appointment;

import com.jakubeeee.client.ClientEntity;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
final class AppointmentCsvMapper {

    static AppointmentEntity map(@NonNull AppointmentCsvRecord csvRecord, ClientEntity client) {
        return new AppointmentEntity(
                csvRecord.getIdentifier(),
                client,
                csvRecord.getStartTime().toInstant(),
                csvRecord.getEndTime().toInstant());
    }
}
