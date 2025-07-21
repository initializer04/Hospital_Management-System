package com.deepakcode.hello.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalAuthentication
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    // ✅ Constructor Injection
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable()) // ❗ Disable CSRF for testing
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/users/register", "/doctors").permitAll() // Public
            .requestMatchers("/users", "/doctors/add", "/patients/add").hasRole("ADMIN") // Admin Only
            .requestMatchers("/appointments/patient/**").hasRole("PATIENT") // Patients Only
            .requestMatchers("/appointments/doctor/**").hasRole("DOCTOR") // Doctors Only
            .anyRequest().authenticated()
        )
        .httpBasic(httpBasic -> {}) // ✅ Use Basic Auth for APIs
        .logout(logout -> logout // ✅ Enable logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")
            .permitAll()
        )
        .build();
}


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }
}
