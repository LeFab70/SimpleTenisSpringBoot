package org.example.tennis.repositories;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class HealthCheckRepository{
    // requete de check db ici juste pour l'exemple on fait simple

    private final EntityManager entityManager;

    public HealthCheckRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Long countApplicationConnections() {
        String applicationConnectionsQuery = "select count(*) from pg_stat_activity where application_name = 'PostgreSQL JDBC Driver'";
        return (Long) entityManager.createNativeQuery(applicationConnectionsQuery).getSingleResult();
    }

}
