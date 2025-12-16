package org.leFab.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Value("${jwt.auth.client-id}")
    private String clientId;

    @Value("${jwt.auth.principal-attribute:preferred_username}")
    private String principalAttribute;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities =
                Stream.of(
                                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                                extractRealmRoles(jwt).stream(),
                                extractClientRoles(jwt).stream()
                        )
                        .flatMap(s -> s)
                        .collect(Collectors.toSet());

        String principal = jwt.getClaimAsString(principalAttribute);

        return new JwtAuthenticationToken(jwt, authorities, principal);
    }

    private Collection<GrantedAuthority> extractRealmRoles(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess == null || !realmAccess.containsKey("roles")) {
            return List.of();
        }

        Collection<String> roles = (Collection<String>) realmAccess.get("roles");
        return roles.stream()
                .map(SimpleGrantedAuthority::new)   // "admin", "user"
                .collect(Collectors.toSet());
    }

    private Collection<GrantedAuthority> extractClientRoles(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess == null || !resourceAccess.containsKey(clientId)) {
            return List.of();
        }

        Map<String, Object> client = (Map<String, Object>) resourceAccess.get(clientId);
        Collection<String> roles = (Collection<String>) client.get("roles");

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}

