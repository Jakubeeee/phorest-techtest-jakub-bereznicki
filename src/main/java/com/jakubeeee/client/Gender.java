package com.jakubeeee.client;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.jakubeeee.misc.CustomCollectors.toSingleton;
import static java.util.Arrays.stream;

@RequiredArgsConstructor
public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private final String rawName;

    public static Gender ofRawName(@NonNull String rawName) {
        return stream(values())
                .filter(gender -> gender.rawName.equals(rawName))
                .collect(toSingleton());
    }
}
