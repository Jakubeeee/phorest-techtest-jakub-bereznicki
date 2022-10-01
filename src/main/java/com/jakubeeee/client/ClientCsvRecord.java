package com.jakubeeee.client;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public final class ClientCsvRecord {
    @CsvBindByName(column = "id", required = true)
    private String identifier;
    @CsvBindByName(column = "first_name", required = true)
    private String firstName;
    @CsvBindByName(column = "last_name", required = true)
    private String lastName;
    @CsvBindByName(column = "email", required = true)
    private String email;
    @CsvBindByName(column = "phone", required = true)
    private String phone;
    @CsvBindByName(column = "gender", required = true)
    private String gender;
    @CsvBindByName(column = "banned", required = true)
    private boolean banned;
}
