package com.igomarcelino.gerenciamento_ordem_de_servico.service;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ClienteDTO.ClienteDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ClienteDTO.ClienteMinDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ClienteDTO.ClienteUpdateDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Cliente;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Endereco;
import com.igomarcelino.gerenciamento_ordem_de_servico.exceptions.ObjectNotFoundException;
import com.igomarcelino.gerenciamento_ordem_de_servico.exceptions.FieldsException;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.ClienteRepository;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.EnderecoRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
     */
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
     */
    @Transactional
    public ClienteDTO findById(Integer id) {
        return clienteRepository.
                findAll().
                stream().
                filter(cliente -> cliente.getId().equals(id)).
                map(ClienteDTO::new).
                findAny().
                orElseThrow(() -> new ObjectNotFoundException("Cliente nao localizado"));
    }

    /**
     * Realiza o cadastro de um cliente
     */
    public ClienteDTO save(Cliente cliente) {
        // Esse metodo cadastra um endereco de forma asincrona e espera o endereco para poder cadastrar o cliente
        CompletableFuture<Endereco> enderecoSaved = enderecoService.enderecoCompletableFuture(cliente.getEndereco());

        try {
            Endereco endereco = enderecoSaved.get();
            cliente.setEndereco(endereco);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erro ao salvar endereco" + e.getMessage());
        }
        clienteRepository.save(cliente);


        return new ClienteDTO(cliente);
    }

    /**
     * Atualiza um cliente
     */
    @Transactional
    public void updateCliente(Integer id, ClienteUpdateDTO clienteUpdateDTO) {
        Optional<Cliente> cliente = clienteRepository.findAll().stream().filter(clienteId -> clienteId.getId().equals(id)).findAny();
        if (cliente.isPresent()){
            cliente.get().setNome(clienteUpdateDTO.getNome());
            cliente.get().setEmail(clienteUpdateDTO.getEmail());
            cliente.get().setTelefone(clienteUpdateDTO.getTelefone());
            cliente.get().setEndereco(clienteUpdateDTO.getEndereco());
            clienteRepository.save(cliente.get());
        }else {
            throw new ObjectNotFoundException("ID %d nao localizado",id);
        }
    }

    /**
     * Deleta um cliente pelo id
     */
    public void deleteById(Integer id) {
        ClienteDTO cliente = findById(id);
        if (cliente != null)
            clienteRepository.deleteById(id);
        else {
            throw new ObjectNotFoundException("Cliente nao existe");
        }
    }

    /**
     * Localiza cliente pelo CPF
     */
    public ClienteDTO findByCPF(String cpf) {
        Optional<ClienteDTO> clienteDTO = clienteRepository.
                findByCPF(cpf).
                map(ClienteDTO::new);
        if (clienteDTO.isPresent()){
            var endereco = enderecoRepository.findByClienteId(clienteDTO.get().getId()).
                    stream().
                    findAny().
                    map(Endereco::new).get();
            clienteDTO.get().setEndereco(endereco);
        }
        return clienteDTO.orElseThrow(()-> new ObjectNotFoundException("CPF: %s nao localizado", cpf));

    }

}