package com.igomarcelino.gerenciamento_ordem_de_servico.controller;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.FuncionarioDTO.FuncionarioDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.FuncionarioDTO.FuncionarioMinDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/funcionario")
@Tag(name = "Funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

   /* @GetMapping
    @Operation(summary = "Retorna todos Funcionarios", description = "Retorna uma lista contendo todos os funcionarios")
    public ResponseEntity<List<FuncionarioMinDTO>> finddAll(){
        return ResponseEntity.ok().body(funcionarioService.findAll());
    }*/

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca pelo ID ", description = "Retorna um Funcionario pelo id caso exista")
    public ResponseEntity<FuncionarioDTO> findByID(Integer id){
        return ResponseEntity.ok().body(funcionarioService.findById(id));
    }

    /*@PutMapping(value = "update/{id}")
    @Operation(summary = "Atualiza pelo id", description = "Atualiza um funcionario pelo ID se existir")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody FuncionarioDTO funcionarioDTO){
        funcionarioService.update(id,funcionarioDTO);
        return ResponseEntity.noContent().build();
    }*/

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deleta pelo id", description = "Deleta um funcionario pelo ID se ele existir")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        funcionarioService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "cpf/{cpf}")
    @Operation(summary = "Busca pelo CPF", description = "Retorna um funcionario buscando pelo cpf")
    public ResponseEntity<FuncionarioDTO> findByCPF(@PathVariable String cpf){
        return ResponseEntity.ok().body(funcionarioService.findByCPF(cpf));
    }

   /* @PostMapping
    @Operation(summary = "Cria funcionario", description = "Cria um funcionario baseado no json informado")
    public ResponseEntity<FuncionarioDTO> save(@RequestBody FuncionarioDTO funcionarioDTO, UriComponentsBuilder builder){
        var funcionarioSaved = funcionarioService.save(funcionarioDTO);
        URI uri = builder.path("funcionario/{id}").buildAndExpand(funcionarioSaved.getId()).toUri();
        return ResponseEntity.created(uri).body(funcionarioSaved);
    }*/

}
