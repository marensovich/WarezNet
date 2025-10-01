package me.marensovich.wareznet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class.
 * <p>
 * Configures JWT authentication, session management, CORS, CSRF, and password encoding.
 * </p>
 *
 * @author marensovich
 * @version v.0.1
 * @since v.0.1
 */
@Configuration
public class SecurityConfig {

    /**
     * Constructs the security configuration.
     *
     * @since v.0.1
     */
    public SecurityConfig() {

    }

    /**
     * Configures the security filter chain.
     *
     * @param http the {@link HttpSecurity} object
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if any error occurs during configuration
     * @since v.0.1
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/v1/**").permitAll()
                        .anyRequest().denyAll())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    /**
     * Configures password encoding using BCrypt.
     *
     * @return a {@link PasswordEncoder} instance
     * @since v.0.1
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}