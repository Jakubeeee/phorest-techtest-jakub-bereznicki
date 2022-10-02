package com.jakubeeee.client;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

record UpdatedClientDTO(
        @NotBlank String identifier,
        String firstName,
        String lastName,
        @Email String email,
        String phone,
        Gender gender,
        boolean banned
) {
}
