package org.leFab.security.service;

import lombok.RequiredArgsConstructor;
import org.leFab.security.entities.RoleEntity;
import org.leFab.security.entities.UserEntity;
import org.leFab.security.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServices implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findOneWithRolesByLoginIgnoreCase(login)
                .map(this::createSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + login));
    }

    private UserDetails createSecurityUser(UserEntity userEntity) {
        List<SimpleGrantedAuthority> authorities = userEntity
                .getRoles()
                .stream()
                .map(RoleEntity::getRole)
                .map(roleEnum -> new SimpleGrantedAuthority("ROLE_" + roleEnum.name()))
                .toList();

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}
