package com.igomarcelino.gerenciamento_ordem_de_servico.controller;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoRequestDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.service.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/ordemServico")
public class OrdemServicoController {

    @Autowired
    OrdemServicoService ordemServicoService;

    @PostMapping
    public ResponseEntity<OrdemServicoDTO> save(@RequestBody OrdemServicoRequestDTO ordemServicoRequestDTO, UriComponentsBuilder builder){
        var ordemServico = new OrdemServicoDTO(ordemServicoRequestDTO);
        URI uri = builder.path("ordemServico/{id}").buildAndExpand(ordemServico.getId()).toUri();
        return ResponseEntity.created(uri).body(ordemServicoService.save(ordemServico));
    }
}
