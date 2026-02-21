package com.reuse.reuse.Security;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private CustomUserDetailsService userDetailsService;

        // 🔐 BCrypt
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        // 🔐 AuthenticationProvider
        @Bean
                public AuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                provider.setUserDetailsService(userDetailsService);
                provider.setPasswordEncoder(passwordEncoder());
                return provider;
        }


        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                        .authenticationProvider(authenticationProvider())
                        // Se definen qué URLs puede usar cada rol
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/login", "/css/**", "/js/**", "/error").permitAll()
                                // .requestMatchers("/proyectos").hasAnyRole("USER", "ADMIN", "COLABORADOR")
                                // .requestMatchers("/proyectos/crear", "/proyectos/eliminar").hasRole("ADMIN")
                                // .requestMatchers("proyectos/crear").hasRole("COLABORADOR")
                                .anyRequest().authenticated()
                        )

                        // Se habilita el formulario de login
                        .formLogin(form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/inicio", true)
                                .failureUrl("/login?error=true")
                                .permitAll()
                        )

                        // Se habilita el logout
                        .logout(logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout=true")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .permitAll()
                        )

                        // Configuración de las sesiones
                        .sessionManagement(session -> session
                                .invalidSessionUrl("/login")  // redirige si la sesión es inválida
                                .maximumSessions(1) // Limita a una session por usuario
                                .expiredUrl("/login")        // redirige si otra sesión expulsó a la actual
                                // .maxSessionsPreventsLogin(true) // Impide nuevos inicios de sesión si se excede el límite );
                        ) 
                                
                                ;

                return http.build();
        }

}
