package com.jakubeeee.client;

import lombok.NonNull;

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

}
