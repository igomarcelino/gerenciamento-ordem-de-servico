package com.igomarcelino.gerenciamento_ordem_de_servico.controller;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.PagamentoDTO.PagamentoDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.PagamentoDTO.PagamentoRequestDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pagamento")
@Tag(name = "Pagamento")
public class PagamentoController {

    @Autowired
    PagamentoService pagamentoService;

    @PostMapping
    @Operation(summary = "Cria um Pagamento", description = "Cria um pagamento e salva no banco de dados")
    public ResponseEntity<PagamentoDTO> save(@RequestBody PagamentoRequestDTO pagamentoDTO, UriComponentsBuilder builder){
        var pagamento = pagamentoService.save(pagamentoDTO);
        URI uri = builder.path("pagamento/{id}").buildAndExpand(pagamento.getId()).toUri();
        return ResponseEntity.created(uri).body(pagamento);
    }

    @GetMapping
    @Operation(summary = "Pagamentos", description = "Retorna uma lista contendo todos os pagamentos")
    public ResponseEntity<List<PagamentoDTO>> findAll(){
        return ResponseEntity.ok().body(pagamentoService.findAll());
    }

}
