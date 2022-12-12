package com.rafoalfaro.server.conf.security;

import com.rafoalfaro.server.conf.jwt.JwtAuthenticationEntryPoint;
import com.rafoalfaro.server.conf.jwt.JwtTokenUtil;
import com.rafoalfaro.server.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    @Autowired
    private UserDetailsServiceImp userDetailsService;
    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthenticateRequestFilter authenticateRequestFilter;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http

            .csrf(csrf->csrf.disable())
            .cors(cors-> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
            .authorizeRequests(request->
                {
                    request.antMatchers("/api/authenticate").permitAll();
                    request.antMatchers("/api/verify").permitAll();
                    request.antMatchers("/api/employee").permitAll();

//                    request.antMatchers("/hello").hasAnyAuthority("ROLE_ADMIN","ROLE_BOSS");
                    request.anyRequest(). authenticated();
                }
            ).addFilterBefore(authenticateRequestFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(handler->handler.authenticationEntryPoint(jwtAuthenticationEntryPoint))
            .build();
    }


    @Bean
    public DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        authenticationManager = authenticationConfiguration.getAuthenticationManager();
        return authenticationManager;
    }





    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
