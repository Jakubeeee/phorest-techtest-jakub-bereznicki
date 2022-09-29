package com.jakubeeee.misc;

import lombok.NonNull;

public final class DataNotFoundException extends RuntimeException {

    public <T extends JpaEntity> DataNotFoundException(@NonNull Class<T> entityType, @NonNull String identifier) {
        super("No data of type <%s> found for identifier <%s>".formatted(entityType, identifier));
    }

}