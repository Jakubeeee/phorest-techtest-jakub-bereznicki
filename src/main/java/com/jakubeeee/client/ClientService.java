package com.jakubeeee.client;

import com.jakubeeee.misc.CsvReader;
import com.jakubeeee.misc.DataNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public void save(@NonNull ClientEntity client) {
        repository.save(client);
    }

    @Transactional
    public void bulkSave(@NonNull InputStream data) {
        CsvReader.read(data, ClientCsvRecord.class)
                .stream()
                .map(ClientCsvMapper::map)
                .forEach(this::save);
    }

    public ClientEntity fetch(@NonNull String identifier) {
        return repository.findByIdentifier(identifier)
                .orElseThrow(() -> new DataNotFoundException(ClientEntity.class, identifier));
    }

    public void update(@NonNull ClientEntity client) {
        repository.save(client);
    }

    public void delete(@NonNull String identifier) {
        repository.deleteByIdentifier(identifier);
    }

}
