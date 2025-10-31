package org.leFab.players.dto;

import jakarta.validation.constraints.*;
import org.leFab.rank.dto.Rank;

import java.time.LocalDate;

public record PlayerRequest(
        @NotNull(message = "Not null player name")
        @NotBlank(message="Not empty player name")
        String firstName,
        String lastName,
        @NotNull(message = "Not null player birth")
        @Past(message = "birth should be not present or future")
//        @Pattern(
//                regexp = "^\\d{4}-\\d{2}-\\d{2}$",
//                message = "Date must be in format yyyy-MM-dd"
//        )
        LocalDate birthDay,
        @NotNull(message = "Not null player rank")
        Rank rank
) {
}
