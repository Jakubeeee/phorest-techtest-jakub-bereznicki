package com.jakubeeee.misc;

import lombok.NoArgsConstructor;

import java.util.stream.Collector;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toUnmodifiableList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CustomCollectors {

    public static <T> Collector<T, ?, T> toSingleton() {
        var exceptionPrefix = "Exactly one element must be present in the stream";
        return collectingAndThen(toUnmodifiableList(), list -> {
            if (list.isEmpty())
                throw new IllegalStateException(exceptionPrefix + "None were found");
            else if (list.size() > 1)
                throw new IllegalStateException(exceptionPrefix + "Multiple were found");
            return list.get(0);
        });
    }

}
