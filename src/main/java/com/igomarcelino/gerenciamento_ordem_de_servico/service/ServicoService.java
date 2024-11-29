package com.igomarcelino.gerenciamento_ordem_de_servico.service;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ServicoDTO.ServicoDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ServicoDTO.ServicoRequestDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Servico;
import com.igomarcelino.gerenciamento_ordem_de_servico.exceptions.DataAlreadyExistsException;
import com.igomarcelino.gerenciamento_ordem_de_servico.exceptions.ObjectNotFoundException;
import com.igomarcelino.gerenciamento_ordem_de_servico.projection.ServicoProjection;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;


@Service
public class ServicoService {

    @Autowired
    ServicoRepository servicoRepository;


    /**
     * Adiciona um Servico
     * */
    public ServicoDTO save(ServicoRequestDTO servicoRequestDTO){
            List<Servico> servicoDTOList = servicoRepository.findAll();
            var servico = new Servico(servicoRequestDTO);
            Collections.sort(servicoDTOList);
            int contains = Collections.binarySearch(servicoDTOList,new Servico(servicoRequestDTO));

           // boolean contains = servicoDTOList.stream().anyMatch(servico1 -> servico1.equals(new Servico(servicoRequestDTO)));
        if (contains < 0){
                servicoRepository.save(servico);
                return new ServicoDTO(servico);
        }else {
            throw new DataAlreadyExistsException("Servico ja Cadastrado");
        }
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
    public List<ServicoDTO> findByDescricao(String descricao){
       // return servicoRepository.findBydescricao(descricao).stream().map(servicoProjections -> servicoProjections.stream().findAny().get()).map(ServicoDTO::new).toList();
        return servicoRepository.findBydescricao(descricao.toLowerCase()).get().stream().map(ServicoDTO::new).toList();
    }

    /**
     * Deleta um servio
     * */
    public void delete(Integer id){
        var servicoToDelete = servicoRepository.findById(id);
        if (!servicoToDelete.isEmpty()){
            servicoRepository.delete(servicoToDelete.get());
        }else {
            throw new ObjectNotFoundException("Servico nao localizado");
        }
    }


}
