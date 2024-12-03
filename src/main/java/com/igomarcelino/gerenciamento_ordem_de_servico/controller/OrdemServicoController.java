package com.igomarcelino.gerenciamento_ordem_de_servico.controller;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoRequestDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Servico;
import com.igomarcelino.gerenciamento_ordem_de_servico.service.OrdemServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/ordemServico")
@Tag(name = "Ordem de Servico")
public class OrdemServicoController {

    @Autowired
    OrdemServicoService ordemServicoService;

    @PostMapping
    @Operation(summary = "Salvar Ordem", description = "Salva uma ordem de servico no banco de dados")
    public ResponseEntity<OrdemServicoDTO> save(@RequestBody OrdemServicoRequestDTO ordemServicoRequestDTO, int[] id_servico, UriComponentsBuilder builder){
        var ordemServico = ordemServicoService.save(ordemServicoRequestDTO,id_servico);
        //URI uri = builder.path("ordemServico/{id}").buildAndExpand(ordemServico.getId()).toUri();
        return ResponseEntity.ok().body(ordemServico);
    }

    @GetMapping
    @Operation(summary = "Lista Ordens", description = "Lista todas as ordens de servico cadastradas")
    public ResponseEntity<List<OrdemServicoDTO>> findAll(){
        return ResponseEntity.ok().body(ordemServicoService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Ordem por ID", description = "Mostra a ordem de servico pelo id dela")
    public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Integer id){
        return ResponseEntity.internalServerError().body(ordemServicoService.findById(id));
    }
}
