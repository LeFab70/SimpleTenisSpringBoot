package org.leFab.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableJpaRepositories(basePackages = {"org.leFab"})
@EntityScan(basePackages = {"org.leFab"})
@SpringBootApplication(scanBasePackages = {"org.leFab"})
public class TennisApplication {

    public static void main(String[] args) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String rawPassword = "fab";
//        String encodedPassword = encoder.encode(rawPassword);
//        System.out.println(encodedPassword);
        SpringApplication.run(TennisApplication.class, args);
    }

}
