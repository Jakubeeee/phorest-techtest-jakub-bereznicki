package com.jakubeeee.client;

import com.jakubeeee.misc.CsvReader;
import com.jakubeeee.misc.DataNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public void save(@NonNull ClientEntity client) {
        repository.save(client);
    }

    @Transactional
    public void bulkSave(@NonNull InputStream data) {
        var clients = CsvReader.read(data, ClientCsvRecord.class)
                .stream()
                .map(ClientCsvMapper::map)
                .toList();
        repository.saveAll(clients);
    }

    public ClientEntity fetch(@NonNull String identifier) {
        return repository.findByIdentifier(identifier)
                .orElseThrow(() -> new DataNotFoundException(ClientEntity.class, identifier));
    }

    public List<ClientEntity> fetch(@NonNull List<String> identifiers) {
        return repository.findAllByIdentifierIn(identifiers);
    }

    public List<ClientEntity> fetchTopClients(int amount, @NonNull LocalDate since) {
        var instant = since.atStartOfDay(ZoneId.of("UTC")).toInstant();
        var pageable = Pageable.ofSize(amount);
        return repository.findTopClients(pageable, instant);
    }

    public void update(@NonNull ClientEntity client) {
        repository.save(client);
    }

    public void delete(@NonNull String identifier) {
        repository.deleteByIdentifier(identifier);
    }

}
