package com.jakubeeee.product;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public final class ProductCsvRecord {
    @CsvBindByName(column = "id", required = true)
    private String identifier;

    @CsvBindByName(column = "appointment_id", required = true)
    private String appointmentIdentifier;

    @CsvBindByName(column = "name", required = true)
    private String name;

    @CsvBindByName(column = "price", required = true)
    private BigDecimal price;

    @CsvBindByName(column = "loyalty_points", required = true)
    private long loyaltyPoints;
}
