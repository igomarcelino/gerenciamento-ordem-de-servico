package com.igomarcelino.gerenciamento_ordem_de_servico.service;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusOrdem;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoRequestDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.OrdemServico;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Servico;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.ServicoBelonging;
import com.igomarcelino.gerenciamento_ordem_de_servico.exceptions.DataAlreadyExistsException;
import com.igomarcelino.gerenciamento_ordem_de_servico.exceptions.FieldsException;
import com.igomarcelino.gerenciamento_ordem_de_servico.exceptions.ObjectNotFoundException;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.OrdemServicoRepository;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.ServicoBelongingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdemServicoService {

    @Autowired
    OrdemServicoRepository ordemServicoRepository;
    @Autowired
    ServicoBelongingRepository servicoBelongingRepository;
    @Autowired
    ServicoService servicoService;

    @Transactional
    public OrdemServicoDTO save(OrdemServicoRequestDTO ordemServicoRequestDTO,int[] id_servicos) {
        var ordemServico = new OrdemServico(ordemServicoRequestDTO);
        List<Servico> servicoList = new ArrayList<>();
        BigDecimal valor = BigDecimal.ZERO;
        for (Integer i : id_servicos){
            var servicoDTO = servicoService.findById(i);
            Servico servico = Servico.fromDto(servicoDTO);
            servicoList.add(servico);
            valor = valor.add(servico.getValor());
        }
        ordemServico.setValor(valor);
        var ordemToSave = ordemServicoRepository.save(ordemServico);

        List<ServicoBelonging> servicoBelongingList = servicoList.stream().map(servico -> new ServicoBelonging(servico,ordemToSave)).toList();
        servicoBelongingRepository.saveAll(servicoBelongingList);
        return new OrdemServicoDTO(ordemToSave);
    }

    public List<OrdemServicoDTO> findAll() {
        return ordemServicoRepository.findAll().
                stream().
                map(OrdemServicoDTO::new).
                toList();
    }
    /**
     * Localizar pelo ID
     * */
    public OrdemServicoDTO findById(Integer id) {
        return ordemServicoRepository.findById(id).map(OrdemServicoDTO::new).get();
    }

    /**
     * Finalizar Ordem de servico
     * */
    public OrdemServicoDTO finalizarOrdem(Integer id, StatusOrdem statusOrdem){
        var ordemServico = ordemServicoRepository.findById(id);
        var ordemAtualizada = new OrdemServico();
        if (ordemServico.get().getStatusOrdem() == StatusOrdem.FINALIZADA){
            throw new DataAlreadyExistsException("A ordem com o id %d ja se encontra finalizada", id);
        } else if (!ordemServico.isEmpty()) {
            ordemServico.get().setStatusOrdem(statusOrdem);
            ordemAtualizada = OrdemServico.verificaStatusOrdem(ordemServico.get());
            ordemServicoRepository.save(ordemAtualizada);
        } else {
            throw new ObjectNotFoundException("A Ordem de numero: %d nao foi localizada!", id);
        }
        return new OrdemServicoDTO(ordemAtualizada);
    }

   public List<OrdemServicoRequestDTO> ordensPorStatus(StatusOrdem statusOrdem){
        return ordemServicoRepository.findByStatus(statusOrdem.name()).stream().map(OrdemServicoRequestDTO::new).toList();
    }



}
