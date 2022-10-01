package com.jakubeeee.tools;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.InputStream;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class FileTools {

    public static InputStream readFile(@NonNull Class<?> relativeClass, @NonNull String fileName) {
        return relativeClass.getResourceAsStream(fileName);
    }
}
