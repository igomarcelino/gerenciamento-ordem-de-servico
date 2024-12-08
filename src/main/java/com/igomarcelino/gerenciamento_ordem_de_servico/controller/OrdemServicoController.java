package com.igomarcelino.gerenciamento_ordem_de_servico.controller;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.FormaPagamento;
import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusOrdem;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoRequestDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoResumeDTO;
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

    @PutMapping (value = "/atualizar/{id}")
    @Operation(summary = "Atualiza Status Ordem", description = "Atualiza o status da ordem de servico")
    public ResponseEntity<OrdemServicoDTO> updateStatus(@PathVariable Integer id, StatusOrdem statusOrdem){
        var ordemUpdated = ordemServicoService.finalizarOrdem(id,statusOrdem);
        return ResponseEntity.ok().body(ordemUpdated);
    }

    @GetMapping(value = "/ordensPorStatus")
    @Operation(summary = "Ordem por status", description = "Lista as ordens de servico pelo status")
    public ResponseEntity<List<OrdemServicoRequestDTO>> findByStatus(StatusOrdem statusOrdem){
        var ordensPorStatus = ordemServicoService.ordensPorStatus(statusOrdem);
        return ResponseEntity.ok().body(ordensPorStatus);
    }

    @GetMapping(value = "/ordemPorCPF")
    @Operation(summary = "Ordem por CPF de cliente", description = "Retorna todas as ordens para determinado cliete")
    public ResponseEntity<List<OrdemServicoResumeDTO>> findbyCPFCliente(String cpf){
        var ordemPorCPF = ordemServicoService.ordemPorCliente(cpf);
        return ResponseEntity.ok().body(ordemPorCPF);
    }

    @PutMapping(value = "/ordemPagamento/{id}")
    public ResponseEntity<OrdemServicoDTO> realizarPagamento(FormaPagamento formaPagamento, @PathVariable Integer id){
        var ordemServico = ordemServicoService.realizarPagamento(formaPagamento,id);
        return ResponseEntity.ok().body(ordemServico);
    }
}
