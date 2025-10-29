package org.leFab.health.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.leFab.Enum.ApplicationStatus;

public record HealthCheck(ApplicationStatus status,
                          @NotBlank(message = "Message cannot be blank")
                          @NotNull(message = "Not null value")
                          String message) {
}
