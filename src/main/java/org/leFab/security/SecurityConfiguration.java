package org.leFab.security;

import lombok.RequiredArgsConstructor;
import org.leFab.security.enums.Roles;
import org.leFab.security.service.UserDetailsServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity //Apply politique to the endpoints
@RequiredArgsConstructor
public class SecurityConfiguration {
   // private final UserDetailsServices userDetailsService;
    private final JwtAuthConverter jwtAuthConverter;
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    };
//    //encoded password
//    @Bean
//public AuthenticationManager authenticationManager(UserDetailsServices userDetailsService, PasswordEncoder passwordEncoder)
//{
//    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//    authProvider.setUserDetailsService(userDetailsService);
//    authProvider.setPasswordEncoder(passwordEncoder);
//    return new ProviderManager(authProvider);
//}

@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
               // .csrf(csrf -> csrf.ignoringRequestMatchers("/api/auth/**"))
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers->
                        headers
                                .contentSecurityPolicy(cps->
                                        cps.policyDirectives("default-src 'self' data:;style-src:'self' 'unsafe-inline'"))
                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::deny)

                                //.permissionsPolicy(permissionsPolicyConfig -> permissionsPolicyConfig.policy(
                                       // "fullscreen=(self), geolocation=(), microphone=(), camera=()"
                                //))
                )

                .authorizeHttpRequests
                (authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.GET,"/api/tennis/players/search","/api/tennis/players").hasAnyAuthority(Roles.user.name(),Roles.admin.name())
                        .requestMatchers(HttpMethod.POST,"/api/tennis/players").hasAuthority(Roles.admin.name())
                        .requestMatchers(HttpMethod.DELETE,"/api/tennis/players/**").hasAuthority(Roles.admin.name())
                        .requestMatchers(HttpMethod.PUT,"/api/tennis/players/**").hasAuthority(Roles.admin.name())
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/api/auth/**","/v3/api-docs/**").permitAll()
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(
                        org.springframework.security.config.http.SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthConverter)));
        return http.build();
}
@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // 👈 autorise Angular
        configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
