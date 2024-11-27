package com.igomarcelino.gerenciamento_ordem_de_servico.service;

import com.igomarcelino.gerenciamento_ordem_de_servico.entities.EnderecoEntity.Endereco;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    /**
     * Metodo responsavel por cadastrar o endereco de forma asincrona, possibilitando
     * que antes de persistir o cliente o endereco esteja persistido
     * */
    @Async
    public CompletableFuture<Endereco> enderecoCompletableFuture(Endereco endereco){
        Endereco enderecoSaved = enderecoRepository.save(endereco);
        return CompletableFuture.completedFuture(enderecoSaved);
    }
}
