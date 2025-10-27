package org.example.tennis.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.tennis.Enum.ApplicationStatus;

public record HealthCheck(ApplicationStatus status,
                          @NotBlank(message = "Message cannot be blank")
                          @NotNull(message = "Not null value")
                          String message) {
}
