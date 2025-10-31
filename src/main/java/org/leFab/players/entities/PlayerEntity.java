package org.leFab.players.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "player")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;
    @Column(name = "first_name",nullable = false,length = 50)
    private String firstName;
    @Column(name = "last_name",length = 50)
    private String lastName;
    @Column(name = "birth_day",nullable = false)
    private LocalDate birthDay;
    @Column(name = "position",nullable = false)
    @Min(value = 0)
    @Max(value = 1000)
    private Integer position;
    @Column(name = "points",nullable = false)
    @Min(value = 0)
    @Max(value = 25000)
    private Integer points;
}
