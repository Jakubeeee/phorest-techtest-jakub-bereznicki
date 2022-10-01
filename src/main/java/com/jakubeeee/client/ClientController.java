package com.jakubeeee.client;

import com.jakubeeee.misc.CsvFile;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static com.jakubeeee.client.ClientDTOMapper.map;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RequiredArgsConstructor
@Validated
@RequestMapping("/client")
@RestController
public class ClientController {

    private final ClientService service;

    @PostMapping(path = "/save", consumes = APPLICATION_JSON_VALUE)
    public void save(@RequestBody @NonNull @Valid ClientDTO client) {
        var entity = map(client);
        service.save(entity);
    }

    @PostMapping(path = "/bulkSave", consumes = MULTIPART_FORM_DATA_VALUE)
    public void bulkSave(@Valid @CsvFile @RequestPart("file") MultipartFile file) throws IOException {
        var data = file.getInputStream();
        service.bulkSave(data);
    }

    @GetMapping(path = "/fetch/{identifier}", produces = APPLICATION_JSON_VALUE)
    public ClientDTO fetch(@PathVariable @NonNull String identifier) {
        var entity = service.fetch(identifier);
        return map(entity);
    }

    @GetMapping(path = "/fetchTopClients", produces = APPLICATION_JSON_VALUE) // TODO local date?
    public List<ClientDTO> fetchTopClients(@RequestParam long amount, @RequestParam LocalDate since) {
        return List.of(); // TODO
    }

    @PatchMapping(path = "/update", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody @NonNull @Valid UpdatedClientDTO client) {
        var currentEntity = service.fetch(client.identifier());
        var updatedEntity = map(client, currentEntity);
        service.update(updatedEntity);
    }

    @DeleteMapping(path = "/delete/{identifier}")
    public void delete(@PathVariable @NonNull String identifier) {
        service.delete(identifier);
    }

}
