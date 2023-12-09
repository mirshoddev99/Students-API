package com.example.StudentsAPI.SecurityConfig;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static com.example.StudentsAPI.Custom.Constants.ALLOWED_ENDPOINTS;
import static com.example.StudentsAPI.Custom.Constants.AUTH_REQUIRED_ENDPOINTS;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, ALLOWED_ENDPOINTS[0]).permitAll()
                .requestMatchers(HttpMethod.POST, AUTH_REQUIRED_ENDPOINTS).hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, AUTH_REQUIRED_ENDPOINTS).hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, ALLOWED_ENDPOINTS).permitAll()
                .anyRequest().authenticated())
                .httpBasic(withDefaults());
        return http.build();
    }

}
