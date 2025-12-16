package org.leFab.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserCredentials(
        @NotBlank(message = "Not blank login")
        @NotEmpty(message = "Not empty login")
        @NotNull(message = "Not null login")
        String login,
        @NotNull(message = "Not null password")
        @NotEmpty(message = "Not empty password")
        @NotBlank(message = "Not blank password")
        String password
) {}
