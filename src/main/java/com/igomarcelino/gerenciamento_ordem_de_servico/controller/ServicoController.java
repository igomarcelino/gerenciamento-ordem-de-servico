package com.igomarcelino.gerenciamento_ordem_de_servico.controller;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ServicoDTO.ServicoDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ServicoDTO.ServicoRequestDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.service.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.PortUnreachableException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/servico")
@Tag(name = "Servico")
public class ServicoController {
    @Autowired
    ServicoService servicoService;

    @PostMapping
    @Operation(summary = "Cadastra Servido", description = "Cadastra um servico fornecendo os atributos segundo a documentacao")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') || hasAuthority('SCOPE_USER')")
    public ResponseEntity<ServicoDTO> save(@RequestBody ServicoRequestDTO servicoRequestDTO, UriComponentsBuilder builder){
        var servico = servicoService.save(servicoRequestDTO);
        URI uri = builder.path("servico/{id}").buildAndExpand(servico.getId()).toUri();
        return ResponseEntity.created(uri).body(servico);
    }

    @GetMapping
    @Operation(summary = "Exibe Servicos", description = "Exibe uma lista contendo todos os servicos cadastrados")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') || hasAuthority('SCOPE_USER')")
    public ResponseEntity<List<ServicoDTO>> findAll(){
        return ResponseEntity.ok().body(servicoService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Localiza por ID", description = "Localiza um servico pelo id do servico")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') || hasAuthority('SCOPE_USER')")
    public ResponseEntity<ServicoDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(servicoService.findById(id));
    }

    @PutMapping("update/{id}")
    @Operation(summary = "Atualiza um Servico", description = "Atualiza um servico pelo id")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable  Integer id, @RequestBody ServicoRequestDTO servicoRequestDTO){
        servicoService.update(id,servicoRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "servico/{descricao}")
    @Operation(summary = "Descricao", description = "Procura os servicos pela descricao")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') || hasAuthority('SCOPE_USER')")
    public ResponseEntity<List<ServicoDTO>> findByDescricao(@PathVariable String descricao){
        return ResponseEntity.ok().body(servicoService.findByDescricao(descricao));
    }

    @DeleteMapping(value = "delete/{id}")
    @Operation(summary = "Deleta um Servico", description = "Deleta um servico pelo id informado")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') || hasAuthority('SCOPE_USER')")
    public ResponseEntity<Void> deteleById(@PathVariable Integer id){
        servicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
