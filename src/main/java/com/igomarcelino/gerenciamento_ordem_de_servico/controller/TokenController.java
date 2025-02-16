package com.igomarcelino.gerenciamento_ordem_de_servico.controller;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.LoginDTO.LoginRequestDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.LoginDTO.LoginResponseDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Roles;
import com.igomarcelino.gerenciamento_ordem_de_servico.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class TokenController {

    @Autowired
    JwtEncoder jwtEncoder;
    @Autowired
    JwtDecoder jwtDecoder;

    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        /**
         * TODO Fazer um metodo para validar o login com os funcionarios do banco de dados
         * */
        var user = funcionarioService.loadUserByUsername(loginRequestDTO.username());

        var now = Instant.now();
        var expiresIn = 1000L;

        var scope = user.getRolesList().stream().map(Roles::getRoleName).collect(Collectors.joining(" "));
        var claims = JwtClaimsSet.builder().
                issuer("back-OrdemServico").
                subject(user.getId().toString()).
                expiresAt(now.plusSeconds(expiresIn)).
                claim("scope", scope).
                issuedAt(now).build();
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return ResponseEntity.ok(new LoginResponseDTO(jwtValue, expiresIn));
    }
}
