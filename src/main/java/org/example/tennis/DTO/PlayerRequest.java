package org.example.tennis.DTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record PlayerRequest(
        @NotNull(message = "Not null player name")
        @NotBlank(message="Not empty player name")
        String firstName,
        String lastName,
        @NotNull(message = "Not null player birth")
        @NotBlank(message="Not empty player birth")
        @Past(message = "birth should not be present or future")
        LocalDate birthDay,
        String rankId
) {
}
