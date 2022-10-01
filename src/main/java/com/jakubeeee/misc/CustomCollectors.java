package com.jakubeeee.misc;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.stream.Collector;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toUnmodifiableList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CustomCollectors {

    /**
     * Returns the only element in the stream.
     *
     * @param <T> the type of stream elements
     * @return the only element in the stream
     * @throws IllegalStateException if there are none or multiple elements in the stream
     */
    public static <T> Collector<T, ?, T> toSingleton() {
        var exceptionPrefix = "Exactly one element must be present in the stream";
        return toSingleton(exceptionPrefix + "None were found", exceptionPrefix + "Multiple were found");
    }

    /**
     * Returns the only element in the stream.
     *
     * @param <T>          the type of stream elements
     * @param aNoneMsg     the exception message if no elements were found
     * @param aMultipleMsg the exception message if multiple elements were found
     * @return the only element in the stream
     * @throws IllegalStateException if there are none or multiple elements in the stream
     */
    public static <T> Collector<T, ?, T> toSingleton(@NonNull String aNoneMsg,
                                                     @NonNull String aMultipleMsg) {
        return collectingAndThen(toUnmodifiableList(), list -> {
            if (list.isEmpty())
                throw new IllegalStateException(aNoneMsg);
            else if (list.size() > 1)
                throw new IllegalStateException(aMultipleMsg);
            return list.get(0);
        });
    }

}
