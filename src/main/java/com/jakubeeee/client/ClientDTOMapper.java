package com.jakubeeee.client;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
final class ClientDTOMapper {

    static ClientDTO map(@NonNull ClientEntity entity) {
        return new ClientDTO(
                entity.getIdentifier(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getGender(),
                entity.isBanned());
    }

    static ClientEntity map(@NonNull ClientDTO dto) {
        return new ClientEntity(
                dto.identifier(),
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.phone(),
                dto.gender(),
                dto.banned());
    }

    static ClientEntity map(@NonNull UpdatedClientDTO dto, @NonNull ClientEntity entity) {
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setEmail(dto.email());
        entity.setPhone(dto.phone());
        entity.setGender(dto.gender());
        entity.setBanned(dto.banned());
        return entity;
    }

}
