package com.jakubeeee.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository repository;

    @BeforeEach
    void setUp() {
        ClientRepositoryTestDataLoader.load(entityManager);
    }

    @Test
    void shouldReturnTopClient_whenLimitingAmountToOne() {
        // GIVEN
        var pageable = Pageable.ofSize(1);

        // WHEN
        var result = repository.findTopClients(pageable, Instant.parse("2000-01-01T00:00:00Z"));

        // THEN
        assertEquals(result.size(), 1);
        var actualTopClient = result.get(0).getIdentifier();
        var expectedTopClient = "test_identifier6";
        assertEquals(expectedTopClient, actualTopClient);
    }

    @Test
    void shouldReturnTop5Clients_whenLimitingAmountTo5() {
        // GIVEN
        var pageable = Pageable.ofSize(5);

        // WHEN
        var result = repository.findTopClients(pageable, Instant.parse("2000-01-01T00:00:00Z"));

        // THEN
        assertEquals(result.size(), 5);
        var actualTopClients = result.stream().map(ClientEntity::getIdentifier).toList();
        var expectedTopClients = List.of("test_identifier6", "test_identifier2", "test_identifier7", "test_identifier5", "test_identifier1");
        assertEquals(expectedTopClients, actualTopClients);
    }

    @Test
    void shouldReturnTop5Clients_whenLimitingAmountTo5AndLimitingTheSinceDate() {
        // GIVEN
        var pageable = Pageable.ofSize(5);

        // WHEN
        var result = repository.findTopClients(pageable, Instant.parse("2015-01-01T00:00:00Z"));

        // THEN
        assertEquals(result.size(), 5);
        var actualTopClients = result.stream().map(ClientEntity::getIdentifier).toList();
        var expectedTopClients = List.of("test_identifier6", "test_identifier1", "test_identifier5", "test_identifier2", "test_identifier8");
        assertEquals(expectedTopClients, actualTopClients);
    }

    @Test
    void shouldReturnNoClients_whenLimitingTheSinceDateToFuture() {
        // GIVEN
        var pageable = Pageable.ofSize(10);

        // WHEN
        var result = repository.findTopClients(pageable, Instant.parse("2025-01-01T00:00:00Z"));

        // THEN
        assertEquals(result.size(), 0);
        var actualTopClients = result.stream().map(ClientEntity::getIdentifier).toList();
        var expectedTopClients = List.of();
        assertEquals(expectedTopClients, actualTopClients);
    }
}
