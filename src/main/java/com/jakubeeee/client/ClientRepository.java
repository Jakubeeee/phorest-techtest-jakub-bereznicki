package com.jakubeeee.client;

import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByIdentifier(@NonNull String identifier);

    void deleteByIdentifier(@NonNull String identifier);

}
