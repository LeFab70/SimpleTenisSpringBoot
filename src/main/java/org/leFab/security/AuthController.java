package org.leFab.security;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Controller", description = "Endpoint for authentication")
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityContextRepository securityContextRepository=new HttpSessionSecurityContextRepository();
    private final SecurityContextLogoutHandler securityContextLogoutHandler=new SecurityContextLogoutHandler();
    @PostMapping("/login")
    public void login(@Valid @RequestBody UserCredentials userCredentials, HttpServletResponse response, HttpServletRequest request){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userCredentials.login(),userCredentials.password());

       Authentication authentication= authenticationManagerBuilder.getObject().authenticate(authenticationToken);
       SecurityContext context=SecurityContextHolder.getContext();
       context.setAuthentication(authentication);
       securityContextRepository.saveContext(context,request,response);

    }

    @GetMapping("/logout")
    public void logout(Authentication authentication, HttpServletResponse response, HttpServletRequest request){

        securityContextLogoutHandler.logout(request,response,authentication);

    }
}
