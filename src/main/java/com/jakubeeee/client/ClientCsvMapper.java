package com.jakubeeee.client;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
final class ClientCsvMapper {

    static ClientEntity map(@NonNull ClientCsvRecord csvRecord) {
        return new ClientEntity(
                csvRecord.getIdentifier(),
                csvRecord.getFirstName(),
                csvRecord.getLastName(),
                csvRecord.getEmail(),
                csvRecord.getPhone(),
                Gender.ofRawName(csvRecord.getGender()),
                csvRecord.isBanned());
    }
}
