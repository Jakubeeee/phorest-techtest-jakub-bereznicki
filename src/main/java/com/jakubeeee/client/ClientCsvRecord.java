package com.jakubeeee.client;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class ClientCsvRecord {
    @CsvBindByName(column = "id", required = true)
    String identifier;
    @CsvBindByName(column = "first_name", required = true)
    String firstName;
    @CsvBindByName(column = "last_name", required = true)
    String lastName;
    @CsvBindByName(column = "email", required = true)
    String email;
    @CsvBindByName(column = "phone", required = true)
    String phone;
    @CsvBindByName(column = "gender", required = true)
    String gender;
    @CsvBindByName(column = "banned", required = true)
    boolean banned;
}
