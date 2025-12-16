package org.leFab.security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user",schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,length = 50,nullable = false,name = "first_name")
    private String firstName;
    @Column(length = 50,nullable = false,name = "last_name")
    private String lastName;
    @Column(unique = true,length = 50,nullable = false)
    private String email;
    @Column(unique = true,length = 10,nullable = false)
    private String login;
    @Column(length = 100,nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles=new HashSet<>();
}
