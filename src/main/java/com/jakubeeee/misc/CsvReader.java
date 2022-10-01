package com.jakubeeee.misc;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CsvReader {

    public static <T> List<T> read(InputStream data, Class<T> type) {
        try (var reader = new BufferedReader(new InputStreamReader(data))) {
            return read(reader, type);
        } catch (IOException e) {
            throw new IllegalStateException("Could not read CSV file");
        }
    }

    private static <T> List<T> read(Reader reader, Class<T> type) {
        return new CsvToBeanBuilder<T>(reader)
                .withType(type)
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build()
                .parse();
    }
}
