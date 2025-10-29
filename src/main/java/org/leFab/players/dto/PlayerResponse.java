package org.leFab.players.dto;

import org.leFab.rank.dto.Rank;

import java.time.LocalDate;

public record PlayerResponse(
        String firstName, String lastName, LocalDate birthDay, Rank rank
        ) {
}
