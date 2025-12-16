package org.leFab.security.entities;

import jakarta.persistence.*;
import lombok.*;
import org.leFab.security.enums.Roles;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "role",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private Roles role;
    }
