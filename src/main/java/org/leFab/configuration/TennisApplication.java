package org.leFab.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"org.leFab"})
@EntityScan(basePackages = {"org.leFab"})
@SpringBootApplication(scanBasePackages = {"org.leFab"})
public class TennisApplication {

    public static void main(String[] args) {
        SpringApplication.run(TennisApplication.class, args);
    }

}
