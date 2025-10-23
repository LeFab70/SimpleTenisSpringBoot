package org.example.tennis.DTO;

import org.example.tennis.Enum.ApplicationStatus;

public record HealthCheck(ApplicationStatus status, String message) {
}
