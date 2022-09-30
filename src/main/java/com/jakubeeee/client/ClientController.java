package com.jakubeeee.client;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RequestMapping("/client")
@RestController
public class ClientController {

    private final ClientService service;

    @PostMapping(path = "/save", consumes = APPLICATION_JSON_VALUE)
    public void save(@RequestBody @NonNull @Valid ClientDTO client) {
        var entity = ClientDTOMapper.map(client);
        service.save(entity);
    }

    @PostMapping(path = "/bulkSave", consumes = APPLICATION_JSON_VALUE)
    public void bulkSave(@RequestBody @NonNull List<ClientDTO> clients) {
        // TODO
    }

    @GetMapping(path = "/fetch/{identifier}", produces = APPLICATION_JSON_VALUE)
    public ClientDTO fetch(@PathVariable @NonNull String identifier) {
        // TODO validate identifier not empty
        var entity = service.fetch(identifier);
        return ClientDTOMapper.map(entity);
    }

    @GetMapping(path = "/fetchTopClients", produces = APPLICATION_JSON_VALUE) // TODO local date?
    public List<ClientDTO> fetchTopClients(@RequestParam long amount, @RequestParam LocalDate since) {
        return List.of(); // TODO
    }

    @PatchMapping(path = "/update", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody @NonNull ClientDTO client) {
        // TODO
    }

    @DeleteMapping(path = "/delete/{identifier}")
    public void delete(@PathVariable @NonNull String identifier) {
        // TODO
    }

}
