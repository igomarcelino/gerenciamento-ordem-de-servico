package com.igomarcelino.gerenciamento_ordem_de_servico.controller;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ClienteDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
@Tag(name = "Cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Clientes", description = "Lista contendo todos os clientes")
    public List<ClienteDTO> findAll(){
        return clienteService.findAll();
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Cliente por ID", description = "Retorna um cliente por id")
    public ClienteDTO findById(@PathVariable Integer id){
        return clienteService.findById(id);
    }

}
