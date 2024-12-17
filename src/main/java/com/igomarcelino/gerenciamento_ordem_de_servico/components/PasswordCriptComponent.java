package com.igomarcelino.gerenciamento_ordem_de_servico.components;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordCriptComponent {


    public String passwordEncoder(String rawPassword){
        return new BCryptPasswordEncoder().encode(rawPassword);
    }


}
