package com.igomarcelino.gerenciamento_ordem_de_servico.service;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ClienteDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Cliente;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Transactional
    public List<ClienteDTO> findAll(){
        return clienteRepository.
                findAll().
                stream().
                map(ClienteDTO::new).
                toList();
    }

    @Transactional
    public ClienteDTO findById(Integer id){
        return clienteRepository.
                findAll().
                stream().
                filter(cliente -> cliente.getId().equals(id)).
                map(ClienteDTO::new).
                findAny().
                get();
    }
}
