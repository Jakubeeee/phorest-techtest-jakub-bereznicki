package com.jakubeeee.client;

import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByIdentifier(@NonNull String identifier);

    List<ClientEntity> findAllByIdentifierIn(@NonNull List<String> identifiers);

    @Query("""
            SELECT client
            FROM ClientEntity client
            JOIN AppointmentEntity appointment ON appointment.client = client
            JOIN ProductEntity product ON product.appointment = appointment
            WHERE client.banned = false
            AND appointment.endTime > :since
            GROUP BY client
            ORDER BY SUM(product.loyaltyPoints) DESC
            """)
    List<ClientEntity> findTopClients(@NonNull Pageable pageable, @Param("since") @NonNull Instant since);

    void deleteByIdentifier(@NonNull String identifier);
}
