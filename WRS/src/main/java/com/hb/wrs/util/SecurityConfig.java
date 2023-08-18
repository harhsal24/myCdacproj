package com.hb.wrs.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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
public class SecurityConfig  {
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(auth -> {
//
//                    auth.requestMatchers("/").permitAll();
//                    auth.requestMatchers("/favicon.ico").permitAll();
//                    auth.anyRequest().authenticated();
//                })
//                .formLogin(Customizer.withDefaults())
//                .build();
//    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.anyRequest().permitAll(); // Allow access to all URLs
                })
                .formLogin(Customizer.withDefaults())
                .build();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails rootUser = User.builder()
                .username("root")
                .password(passwordEncoder().encode("root"))
                .roles("SUPER_ADMIN")
                .build();

        UserDetails superadminUser = User.builder()
                .username("superadmin")
                .password(passwordEncoder().encode("root"))
                .roles("SUPER_ADMIN")
                .build();

        UserDetails teamLeaderUser = User.builder()
                .username("teamleader")
                .password(passwordEncoder().encode("root"))
                .roles("TEAM_LEADER")
                .build();

        UserDetails employeeUser = User.builder()
                .username("employee")
                .password(passwordEncoder().encode("root"))
                .roles("REGULAR_EMPLOYEE")
                .build();

        return new InMemoryUserDetailsManager(rootUser, superadminUser, teamLeaderUser, employeeUser);
    }



}
