package com.igomarcelino.gerenciamento_ordem_de_servico.service;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ServicoDTO.ServicoDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ServicoDTO.ServicoRequestDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Servico;
import com.igomarcelino.gerenciamento_ordem_de_servico.exceptions.ObjectNotFoundException;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    @Autowired
    ServicoRepository servicoRepository;


    /**
     * Adiciona um Servico
     * */
    public ServicoDTO save(ServicoRequestDTO servicoRequestDTO){
            var servico = new Servico(servicoRequestDTO);
            servicoRepository.save(servico);
            return new ServicoDTO(servico);
    }

    /**
     * Localiza todos os servicos
     * */
    public List<ServicoDTO> findAll(){
        return servicoRepository.findAll().
                stream().
                map(ServicoDTO::new).
                toList();
    }

    /**
     * Localiza pelo id
     * */
    public ServicoDTO findById(Integer id){
        return servicoRepository.
                findById(id).
                map(ServicoDTO::new).
                orElseThrow(()-> new ObjectNotFoundException("Id %d nao foi localizado ", id));
    }

    /**
     * Atualiza pelo id
     * */
    public void update(Integer id, ServicoRequestDTO servicoRequestDTO){
        var servicoToUpdate = servicoRepository.findById(id);
        if (!servicoToUpdate.isEmpty()){
            servicoToUpdate.get().setDescricao(servicoRequestDTO.getDescricao());
            servicoToUpdate.get().setValor(servicoRequestDTO.getValor());
            servicoRepository.save(servicoToUpdate.get());
        }else {
            throw new ObjectNotFoundException("O ID %d nao foi localizado", id);
        }
    }

    /**
     * Procura por descricao
     * */
  /*  public List<ServicoDTO> findByDescricao(String descricao){
        return servicoRepository.findBydescricao(descricao).map(ServicoDTO::new)

    }*/



}
