package org.example.tennis.restControllers;

import org.example.tennis.DTO.HealthCheck;
import org.example.tennis.services.interfaces.HealthCheckService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/health")// Endpoint to check application health
    public HealthCheck getHealthCheck() {
        return healthCheckService.getHealthCheck();
    }
}
