package com.jakubeeee.client;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

record ClientDTO(
        @NotBlank String identifier,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email,
        @NotBlank String phone,
        @NotNull Gender gender,
        boolean banned
) {
}
