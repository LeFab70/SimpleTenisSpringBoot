package org.example.tennis.DTO;

import java.time.LocalDate;

public record PlayerResponse(
        String firstName, String lastName, LocalDate birthDay, Rank rank
        ) {
}
