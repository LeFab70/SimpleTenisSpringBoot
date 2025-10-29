package org.leFab.rank.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record Rank(
        @Positive(message = "must positive ") @Min(message = "min value 1",value = 1) @Max(message = "max value 20",value = 200)
        Integer position,
        @PositiveOrZero(message = "must positive or 0")
        Integer points) {
}
