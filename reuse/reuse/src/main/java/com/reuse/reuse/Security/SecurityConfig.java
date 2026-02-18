package com.reuse.reuse.Security;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// import com.reuse.reuse.Entity.Usuario;
// import com.reuse.reuse.Service.UsuarioService;

@Configuration
public class SecurityConfig {

        

        @Autowired
        private CustomUserDetailsService userDetailsService;

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

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
                                .requestMatchers("/css/**", "/login").permitAll()
                                // .requestMatchers("/proyectos").hasAnyRole("USER", "ADMIN", "COLABORADOR")
                                // .requestMatchers("/proyectos/crear", "/proyectos/eliminar").hasRole("ADMIN")
                                // .requestMatchers("proyectos/crear").hasRole("COLABORADOR")
                                .anyRequest().authenticated())

                        // Se habilita el formulario de login
                        .formLogin(form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/inicio", true)
                                .permitAll())

                        // Se habilita el logout
                        .logout(logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .permitAll());

                return http.build();
        }

        // @Bean
        // public CommandLineRunner migrarPasswords(
        //         UsuarioService usuarioService,
        //         PasswordEncoder passwordEncoder) {

        // return args -> {

        //         List<Usuario> usuarios = usuarioService.listar();

        //         for (Usuario u : usuarios) {

        //         // Solo encripta si aún NO está encriptado
        //         if (!u.getPassword().startsWith("$2a$")) {

        //                 String hash = passwordEncoder.encode(u.getPassword());
        //                 u.setPassword(hash);
        //                 usuarioService.actualizar(u);

        //                 System.out.println("Password migrado para: " + u.getUsername());
        //         }
        //         }

        //         System.out.println("Migración completada.");
        // };
        // }


}
