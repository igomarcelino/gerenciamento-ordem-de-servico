package com.igomarcelino.gerenciamento_ordem_de_servico.controller;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.FuncionarioDTO.FuncionarioDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.FuncionarioDTO.FuncionarioMinDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/funcionario")
@Tag(name = "Funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping
    @Operation(summary = "Retorna todos Funcionarios", description = "Retorna uma lista contendo todos os funcionarios")
    public ResponseEntity<List<FuncionarioMinDTO>> finddAll(){
        return ResponseEntity.ok().body(funcionarioService.findAll());
    }


}
