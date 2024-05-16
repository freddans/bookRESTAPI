package com.example.bookbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf // Opening up POST, PUT and DELETE in endpoint
                        .ignoringRequestMatchers("/admin/**")
                        .ignoringRequestMatchers("/booking/**")
                        .ignoringRequestMatchers("/eventbooking/**")
                        .ignoringRequestMatchers("/event/**")
                        .ignoringRequestMatchers("/flight/**")
                        .ignoringRequestMatchers("/hotel/**")
                        .ignoringRequestMatchers("/transportation/**")
                        .ignoringRequestMatchers("/package/**")
                        .ignoringRequestMatchers("/user/**")
                )
                // making endpoints usable
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/**").permitAll()
                        .requestMatchers("/booking/**").permitAll()
                        .requestMatchers("/eventbooking/**").permitAll()
                        .requestMatchers("/event/**").permitAll()
                        .requestMatchers("/flight/**").permitAll()
                        .requestMatchers("/hotel/**").permitAll()
                        .requestMatchers("/transportation/**").permitAll()
                        .requestMatchers("/package/**").permitAll()
                        .requestMatchers("/user/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.builder()
                .username("u")
                .password(passwordEncoder().encode("u"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("a")
                .password(passwordEncoder().encode("a"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
