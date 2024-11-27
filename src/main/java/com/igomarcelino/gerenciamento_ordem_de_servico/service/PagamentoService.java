package com.igomarcelino.gerenciamento_ordem_de_servico.service;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.PagamentoDTO.PagamentoDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.PagamentoDTO.PagamentoRequestDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Pagamento;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {
    @Autowired
    PagamentoRepository pagamentoRepository;

    /**
     * Salva um pagamento
     * */
    public PagamentoDTO save(PagamentoRequestDTO pagamentoDTO){
        var pagamento = new Pagamento(pagamentoDTO);
        pagamentoRepository.save(pagamento);
        return new PagamentoDTO(pagamento);
    }

    /**
     * Retorna todos pagamentos
     * */
    public List<PagamentoDTO> findAll(){
        return pagamentoRepository.findAll().
                stream().
                map(PagamentoDTO::new).
                toList();
    }

}
