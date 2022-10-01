package com.jakubeeee.appointment;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public final class AppointmentCsvRecord {
    @CsvBindByName(column = "id", required = true)
    private String identifier;

    @CsvBindByName(column = "client_id", required = true)
    private String clientIdentifier;

    @CsvDate(value = "yyyy-MM-dd HH:mm:ss X")
    @CsvBindByName(column = "start_time", required = true)
    private OffsetDateTime startTime;

    @CsvDate(value = "yyyy-MM-dd HH:mm:ss X")
    @CsvBindByName(column = "end_time", required = true)
    private OffsetDateTime endTime;
}
