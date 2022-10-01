package com.jakubeeee.client;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByIdentifier(@NonNull String identifier);

    List<ClientEntity> findAllByIdentifierIn(@NonNull List<String> identifiers);

    void deleteByIdentifier(@NonNull String identifier);

}
