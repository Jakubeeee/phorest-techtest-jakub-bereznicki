package com.jakubeeee.tools;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.InputStream;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class FileTools {

    public static InputStream readFile(@NonNull Class<?> relativeClass, @NonNull String fileName) {
        var stream = relativeClass.getResourceAsStream(fileName);
        if (stream == null)
            throw new IllegalStateException("Could not find file: <%s>".formatted(fileName));
        return stream;
    }
}
