package com.igomarcelino.gerenciamento_ordem_de_servico.service;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ClienteDTO.ClienteDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ClienteDTO.ClienteMinDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ClienteDTO.ClienteUpdateDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Cliente;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Endereco;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.ClienteRepository;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    EnderecoService enderecoService;

    /**
     * Retorna todos os clientes com iformacoes reduzidas
     * */
    @Transactional
    public List<ClienteMinDTO> findAll() {
        return clienteRepository.
                findAll().
                stream().
                map(ClienteMinDTO::new).
                toList();
    }

    /**
     * Retorna um cliente com informacoes completas
     * */
    @Transactional
    public ClienteDTO findById(Integer id) {
        return clienteRepository.
                findAll().
                stream().
                filter(cliente -> cliente.getId().equals(id)).
                map(ClienteDTO::new).
                findAny().
                get();
    }

    /**
     * Realiza o cadastro de um cliente
     * */
    public void save(Cliente cliente) {
        // Esse metodo cadastra um endereco de forma asincrona e espera o endereco para poder cadastrar o cliente
        CompletableFuture<Endereco> enderecoSaved = enderecoService.enderecoCompletableFuture(cliente.getEndereco());

            try {
                Endereco endereco = enderecoSaved.get();
                cliente.setEndereco(endereco);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Erro ao salvar endereco" + e.getMessage());
            }
        clienteRepository.save(cliente);
    }

    /**
     * Atualiza um cliente
     * */
    @Transactional
    public void updateCliente(Integer id,ClienteUpdateDTO clienteUpdateDTO){
        Cliente cliente = clienteRepository.findAll().stream().filter(clienteId -> clienteId.getId().equals(id)).findAny().get();
        cliente.setNome(clienteUpdateDTO.getNome());
        cliente.setEmail(clienteUpdateDTO.getEmail());
        cliente.setTelefone(clienteUpdateDTO.getTelefone());
        cliente.setEndereco(clienteUpdateDTO.getEndereco());
        clienteRepository.save(cliente);
    }

    /**
     * Deleta um cliente pelo id
     * */
    public void deleteById(Integer id){
        clienteRepository.deleteById(id);
    }
}
