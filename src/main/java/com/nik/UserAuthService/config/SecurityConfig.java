package com.nik.UserAuthService.config;

import com.nik.UserAuthService.security.JwtAuthenticationEntryPoint;
import com.nik.UserAuthService.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(AbstractHttpConfigurer::disable) // Disable Spring Security's CORS handling
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth->auth
                    .requestMatchers("/register").permitAll()
                    .requestMatchers("/register/**").permitAll()
                    .requestMatchers("/auth/login").permitAll()
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/**").permitAll()  // Temporarily allow all to test
                    .anyRequest().permitAll())  // Temporarily permit all
            .exceptionHandling(ex->ex.authenticationEntryPoint(point))
            .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // Temporarily comment out the JWT filter to test
        // http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager doAuthenticate(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}