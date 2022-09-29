package com.jakubeeee.client;

import lombok.NonNull;

public record ClientDTO(
        @NonNull String identifier,
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull String email,
        @NonNull String phone,
        @NonNull Gender gender,
        boolean banned
) {
}
