package com.jakubeeee.client;

import com.jakubeeee.misc.DataNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public void save(@NonNull ClientEntity client) {
        repository.save(client);
    }

    public ClientEntity fetch(@NonNull String identifier) {
        return repository.findByIdentifier(identifier)
                .orElseThrow(() -> new DataNotFoundException(ClientEntity.class, identifier));
    }

}
