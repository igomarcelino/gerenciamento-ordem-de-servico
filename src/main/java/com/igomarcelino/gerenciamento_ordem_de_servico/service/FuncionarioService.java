package com.igomarcelino.gerenciamento_ordem_de_servico.service;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.FuncionarioDTO.FuncionarioDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.FuncionarioDTO.FuncionarioMinDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    FuncionarioRepository funcionarioRepository;

    /**
     * Metodo que retorna todos funcionarios
     * */

    public List<FuncionarioMinDTO> findAll(){
        return funcionarioRepository.findAll().
                stream().
                map(FuncionarioMinDTO::new).
                toList();
    }
}
