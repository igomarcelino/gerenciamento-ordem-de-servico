package com.igomarcelino.gerenciamento_ordem_de_servico.service;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoRequestDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.OrdemServico;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemServicoService {

    @Autowired
    OrdemServicoRepository ordemServicoRepository;

    public OrdemServicoDTO save(OrdemServicoDTO ordemServicoDTO){
        var ordemServico = new OrdemServico(ordemServicoDTO);
        ordemServicoRepository.save(ordemServico);
        return new OrdemServicoDTO(ordemServico);
    }

    public List<OrdemServicoDTO> findAll(){
        return ordemServicoRepository.findAll().
                stream().
                map(OrdemServicoDTO::new).
                toList();
    }

    public OrdemServicoDTO findById(Integer id){
        return ordemServicoRepository.findById(id).map(OrdemServicoDTO::new).get();
    }

}
