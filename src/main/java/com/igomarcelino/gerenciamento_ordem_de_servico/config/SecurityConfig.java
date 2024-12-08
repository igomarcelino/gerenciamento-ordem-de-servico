package com.igomarcelino.gerenciamento_ordem_de_servico.config;

import com.igomarcelino.gerenciamento_ordem_de_servico.service.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    FuncionarioService funcionarioService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

        return security.formLogin(Customizer.withDefaults()).authorizeHttpRequests(aut ->
                        aut.requestMatchers("/login/**").permitAll().
                                requestMatchers("/admin/**").hasAnyAuthority("admin").
                                requestMatchers("/users/**").hasAnyAuthority("admin", "user").
                                anyRequest().authenticated()).csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**") // Desativa CSRF para o H2 Console
                )
                .headers(headers -> headers
                        .frameOptions().disable() // Permite que o H2 Console use frames
                ).userDetailsService(funcionarioService).build();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
