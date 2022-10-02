package com.jakubeeee.client;

import com.jakubeeee.misc.CsvReader;
import com.jakubeeee.misc.DataNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public void save(@NonNull ClientEntity client) {
        repository.save(client);
    }

    /**
     * Saves multiple {@link ClientEntity} data by parsing provided CSV file.
     *
     * @param data the CSV file
     */
    @Transactional
    public void bulkSave(@NonNull InputStream data) {
        var clients = CsvReader.read(data, ClientCsvRecord.class)
                .stream()
                .map(ClientCsvMapper::map)
                .toList();
        log.info("Bulk saving <%s> clients".formatted(clients.size()));
        repository.saveAll(clients);
    }

    public ClientEntity fetch(@NonNull String identifier) {
        return repository.findByIdentifier(identifier)
                .orElseThrow(() -> new DataNotFoundException(ClientEntity.class, identifier));
    }

    public List<ClientEntity> fetch(@NonNull List<String> identifiers) {
        return repository.findAllByIdentifierIn(identifiers);
    }

    /**
     * Fetches the requested amount of top clients that accumulated the most loyalty points since given date.
     *
     * @param amount the amount of top clients to be fetched
     * @param since  the UTC date since the loyalty points are taken into account
     * @return the top clients
     */
    public List<ClientEntity> fetchTopClients(int amount, @NonNull LocalDate since) {
        var sinceInstant = since.atStartOfDay(ZoneId.of("UTC")).toInstant();
        var pageable = Pageable.ofSize(amount);
        return repository.findTopClients(pageable, sinceInstant);
    }

    public void update(@NonNull ClientEntity client) {
        repository.save(client);
    }

    public void delete(@NonNull String identifier) {
        repository.deleteByIdentifier(identifier);
    }

}
