package com.jakubeeee.appointment;

import com.jakubeeee.misc.CsvFile;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RequiredArgsConstructor
@Validated
@RequestMapping("/appointment")
@RestController
public class AppointmentController {

    private final AppointmentService service;

    @PostMapping(path = "/bulkSave", consumes = MULTIPART_FORM_DATA_VALUE)
    public void bulkSave(@Valid @CsvFile @RequestPart("file") MultipartFile file) throws IOException {
        var data = file.getInputStream();
        service.bulkSave(data);
    }

}
