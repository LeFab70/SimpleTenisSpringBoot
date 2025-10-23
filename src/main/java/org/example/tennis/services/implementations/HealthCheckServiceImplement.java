package org.example.tennis.services.implementations;

import org.example.tennis.Enum.ApplicationStatus;
import org.example.tennis.DTO.HealthCheck;
import org.example.tennis.repositories.HealthCheckRepository;
import org.example.tennis.services.interfaces.HealthCheckService;
import org.springframework.stereotype.Service;

@Service
// Service implementation for health check functionality
public class HealthCheckServiceImplement implements HealthCheckService {

    //injection of dependencies if needed (none in this case) repositories, other services, etc.
    private final HealthCheckRepository healthCheckRepository;

    public HealthCheckServiceImplement(HealthCheckRepository healthCheckRepository) {
        this.healthCheckRepository = healthCheckRepository;
    }


    // Implementation of the health check method

    @Override
    public HealthCheck getHealthCheck() {
       Long connectionCount = healthCheckRepository.countApplicationConnections();
       connectionCount = (connectionCount == null) ? 0 : connectionCount;

      if(connectionCount==0) {
          return new HealthCheck(ApplicationStatus.ERROR, "the application has no active database connections.");
      }
               else{
                   return new HealthCheck(ApplicationStatus.OK, "The application is running smoothly."+
                           " Active database connections: " + connectionCount);
      }

    }
}
