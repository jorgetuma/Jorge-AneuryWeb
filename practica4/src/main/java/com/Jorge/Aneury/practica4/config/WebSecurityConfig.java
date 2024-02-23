package com.Jorge.Aneury.practica4.config;

import com.Jorge.Aneury.practica4.servicios.SecurityServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private SecurityServices securityServices;
    private PasswordEncoder passwordEncoder;

    public WebSecurityConfig(SecurityServices securityServices, PasswordEncoder passwordEncoder) {
        this.securityServices = securityServices;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers( "/favicon.ico", "/{project}/{id}").permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/usuario/**")).hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/"))
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/{project}/{id}"));
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        auth.userDetailsService(securityServices)
                .passwordEncoder(passwordEncoder);

        return auth.build();
    }

}
