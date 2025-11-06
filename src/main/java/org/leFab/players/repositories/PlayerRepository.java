package org.leFab.players.repositories;

import org.leFab.players.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    //Optional<PlayerEntity> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<PlayerEntity> findByFirstNameAndLastNameAndBirthDay(String firstName, String lastName, LocalDate birthDay);
}
