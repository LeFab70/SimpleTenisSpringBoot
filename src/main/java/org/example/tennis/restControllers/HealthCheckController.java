package org.example.tennis.restControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.tennis.DTO.HealthCheck;
import org.example.tennis.services.interfaces.HealthCheckService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health Check Controller", description = "Endpoint for checking application health")
@RestController
@RequestMapping("/api") // Base path for all endpoints in this controller
public class HealthCheckController {


    //anciennement
    //@Autowired
    //private  HealthCheckService healthCheckService;

    //new version with constructor injection
    private final HealthCheckService healthCheckService;
    public HealthCheckController(HealthCheckService healthCheckService){
        this.healthCheckService = healthCheckService;
    }

    @Operation(summary = "Get Application Health", description = "Retrieves the current health status of the application.")
    @GetMapping("/health")// Endpoint to check application health
    public HealthCheck getHealthCheck() {
        return healthCheckService.getHealthCheck();
    }
}
