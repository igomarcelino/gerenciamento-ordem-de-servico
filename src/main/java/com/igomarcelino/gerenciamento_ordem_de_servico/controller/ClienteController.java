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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
@Tag(name = "Cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Clientes", description = "Lista contendo todos os clientes")
    public ResponseEntity<List<ClienteMinDTO>> findAll(){
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Cliente por ID", description = "Retorna um cliente por id")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(clienteService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Salvar cliente", description = "Salva um cliente")
    public ResponseEntity<ClienteDTO> save(@RequestBody Cliente cliente){
        ClienteDTO clienteDTO = clienteService.save(cliente);

        return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta pelo ID", description = "Deleta um cliente pelo id informado")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(value = "update/{id}")
    @Operation(summary = "Atualiza Cliente" , description = "Atualiza um cliente existente")
    public ResponseEntity<Void> updateCliente(@PathVariable Integer id, @RequestBody ClienteUpdateDTO clienteUpdateDTO){
        clienteService.updateCliente(id,clienteUpdateDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/cliente/{cpf}")
    @Operation(summary = "Localiza por CPF", description = "Localiza um cliente pelo cpf passado")
    public ResponseEntity<ClienteDTO> findByCPF(@PathVariable String cpf){
        ClienteDTO clienteDTO =  clienteService.findByCPF(cpf);
        return ResponseEntity.ok().body(clienteDTO);
    }

}
