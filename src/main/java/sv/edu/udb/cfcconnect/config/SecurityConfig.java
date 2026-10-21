package sv.edu.udb.cfcconnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cursos/**", "/diplomados/**", "/espacios/**", "/catering/**")
                            .hasAnyRole("ADMIN", "RECEPCIONISTA", "CLIENTE", "CONTABILIDAD")
                        .requestMatchers("/roles/**", "/usuarios/**").hasRole("ADMIN")
                        .requestMatchers("/clientes/**").hasAnyRole("ADMIN", "RECEPCIONISTA")
                        .requestMatchers(HttpMethod.POST, "/inscripciones", "/cotizaciones").hasRole("CLIENTE")
                        .requestMatchers("/cursos/**", "/diplomados/**", "/espacios/**", "/catering/**")
                            .hasAnyRole("ADMIN", "RECEPCIONISTA")
                        .requestMatchers("/inscripciones/**", "/cotizaciones/**")
                            .hasAnyRole("ADMIN", "RECEPCIONISTA")
                        .requestMatchers("/pagos/**").hasAnyRole("ADMIN", "CONTABILIDAD")
                        .anyRequest().denyAll());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
