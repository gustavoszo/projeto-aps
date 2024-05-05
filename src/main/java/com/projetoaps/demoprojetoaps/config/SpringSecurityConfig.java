package com.projetoaps.demoprojetoaps.config;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.projetoaps.demoprojetoaps.security.SecurityUserDetailsService;

import com.projetoaps.demoprojetoaps.entity.Usuario.Role;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    antMatcher("/css/**"),
                    antMatcher("/js/**"),
                    antMatcher("/resources/**"),
                    antMatcher("/errors/**"),
                    antMatcher(HttpMethod.GET, "/"),
                    antMatcher(HttpMethod.GET, "/covid/**")
                ).permitAll()
                .requestMatchers(HttpMethod.GET, "/admin/**").hasAuthority(Role.ROLE_ADMIN.name())
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/admin", true)
                // .failureUrl("/login-error")
                .permitAll()
            )
            .logout( logout -> logout
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .build();
    }

    @SuppressWarnings("removal")
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, SecurityUserDetailsService userDetailsService) throws Exception {
        // O método getShared.. registra o authenticationManager
        return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder)
        .and()
        .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
