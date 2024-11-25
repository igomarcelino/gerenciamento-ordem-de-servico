package com.igomarcelino.gerenciamento_ordem_de_servico.controller;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ClienteDTO.ClienteDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ClienteDTO.ClienteMinDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ClienteDTO.ClienteUpdateDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Cliente;
import com.igomarcelino.gerenciamento_ordem_de_servico.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
@Tag(name = "Cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Clientes", description = "Lista contendo todos os clientes")
    public List<ClienteMinDTO> findAll(){
        return clienteService.findAll();
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Cliente por ID", description = "Retorna um cliente por id")
    public ClienteDTO findById(@PathVariable Integer id){
        return clienteService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Salvar cliente", description = "Salva um cliente")
    public void save(@RequestBody Cliente cliente){
        clienteService.save(cliente);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta pelo ID", description = "Deleta um cliente pelo id informado")
    public void deleteById(@PathVariable Integer id){
        clienteService.deleteById(id);
    }


    @PutMapping(value = "update/{id}")
    @Operation(summary = "Atualiza Cliente" , description = "Atualiza um cliente existente")
    public void updateCliente(@PathVariable Integer id, @RequestBody ClienteUpdateDTO clienteUpdateDTO){
        clienteService.updateCliente(id,clienteUpdateDTO);
    }


}
