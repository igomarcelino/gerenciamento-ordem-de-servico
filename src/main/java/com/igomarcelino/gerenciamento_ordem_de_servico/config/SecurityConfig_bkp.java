package com.igomarcelino.gerenciamento_ordem_de_servico.config;

import com.igomarcelino.gerenciamento_ordem_de_servico.service.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;



public class SecurityConfig_bkp {


    /*FuncionarioService funcionarioService;


    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security.authorizeHttpRequests(aut ->
                        aut.requestMatchers("/login/**").permitAll(). // qualquer um pode visualizar a tela de login
                                requestMatchers("/h2-console/**").hasAuthority("admin").
                                requestMatchers(HttpMethod.POST).hasAnyAuthority("user", "admin").
                                requestMatchers(HttpMethod.GET).hasAnyAuthority("user", "admin").
                                requestMatchers(HttpMethod.PUT).hasAnyAuthority("user", "admin").
                                requestMatchers(HttpMethod.DELETE, "/**").hasAuthority("admin"). // permite acesso a todos os metodos e endpoints
                                anyRequest().authenticated()).csrf(csrf -> csrf
                        .ignoringRequestMatchers("/**") // Desativa CSRF para toda API
                )
                .headers(headers -> headers
                        .frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()) // Permite que o H2 Console use frames
                ).httpBasic(withDefaults()).userDetailsService(funcionarioService).build();
    }



    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
*/

}
