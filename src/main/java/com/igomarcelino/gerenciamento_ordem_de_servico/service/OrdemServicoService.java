package com.igomarcelino.gerenciamento_ordem_de_servico.service;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.AutorizarOrdemServico;
import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.FormaPagamento;
import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusOrdem;
import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusPagamento;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemAprovacaoClienteDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoRequestDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoResumeDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.PagamentoDTO.PagamentoRequestDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.OrdemServico;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Pagamento;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Servico;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.ServicoBelonging;
import com.igomarcelino.gerenciamento_ordem_de_servico.exceptions.DataAlreadyExistsException;
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
    @Autowired
    PagamentoService pagamentoService;

    /**
     * Gerar ordem de servico
     * */
    @Transactional
    public OrdemServicoDTO save(OrdemServicoRequestDTO ordemServicoRequestDTO, int[] id_servicos) {
        var ordemServico = new OrdemServico(ordemServicoRequestDTO);
        List<Servico> servicoList = new ArrayList<>();
        BigDecimal valor = BigDecimal.ZERO;
        for (Integer i : id_servicos) {
            var servicoDTO = servicoService.findById(i);
            Servico servico = Servico.fromDto(servicoDTO);
            servicoList.add(servico);
            valor = valor.add(servico.getValor());
        }
        ordemServico.setValor(valor);
        var ordemToSave = ordemServicoRepository.save(ordemServico);

        List<ServicoBelonging> servicoBelongingList = servicoList.stream().map(servico -> new ServicoBelonging(servico, ordemToSave)).toList();
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
     */
    public OrdemServicoDTO findById(Integer id) {
        return ordemServicoRepository.findById(id).map(OrdemServicoDTO::new).get();
    }

    /**
     * Finalizar Ordem de servico
     */
    public OrdemServicoDTO finalizarOrdem(Integer id, StatusOrdem statusOrdem) {
        var ordemServico = ordemServicoRepository.findById(id);
        var ordemAtualizada = new OrdemServico();
        if (ordemServico.get().getStatusOrdem() == StatusOrdem.APROVADA_PELO_CLIENTE){
            if (ordemServico.get().getStatusOrdem() == StatusOrdem.FINALIZADA) {
                throw new DataAlreadyExistsException("A ordem com o id %d ja se encontra finalizada", id);
            } else if (!ordemServico.isEmpty()) {
                ordemServico.get().setStatusOrdem(statusOrdem);
                ordemAtualizada = OrdemServico.verificaStatusOrdem(ordemServico.get());
                ordemServicoRepository.save(ordemAtualizada);
            } else {
                throw new ObjectNotFoundException("A Ordem de numero: %d nao foi localizada!", id);
            }
            return new OrdemServicoDTO(ordemAtualizada);
        }else {
            throw new ObjectNotFoundException("A ordem de servico de numero %d nao foi aprovada pelo cliente", id);
        }
    }

    /**
     * Ordens por status
     */
    public List<OrdemServicoRequestDTO> ordensPorStatus(StatusOrdem statusOrdem) {
        var ordemPorStatus = ordemServicoRepository.findByStatus(statusOrdem.name()).stream().map(OrdemServicoRequestDTO::new).toList();
        if (ordemPorStatus.size() != 0){
            return ordemPorStatus;
        }else {
            throw new ObjectNotFoundException("Nao possuem ordem com o status %s para listagem.",statusOrdem.name().replace("_"," "));
        }
    }

    /**
     * Ordens por Cliente, procura pelo cpf
     */
    public List<OrdemServicoResumeDTO> ordemPorCliente(String cpf) {
        var ordensPorCliente = ordemServicoRepository.findByCpfCliente(cpf).get().stream().map(OrdemServicoResumeDTO::new).toList();
        if (ordensPorCliente.size() != 0) {
            return ordensPorCliente;
        } else {
            throw new ObjectNotFoundException("O cpf: %s nao possui ordens de servico.", cpf);
        }
    }

    /**
     * Realiza o pagamento da ordem
     */
    public OrdemServicoDTO realizarPagamento(FormaPagamento formaPagamento, Integer id) {
        var ordemServico = ordemServicoRepository.findById(id);
        if (!ordemServico.isEmpty()) {
            if (ordemServico.get().getStatusOrdem() == StatusOrdem.FINALIZADA) {
                var pagamento = new Pagamento();
                pagamento.setFormaPagamento(formaPagamento);
                pagamento.setValor(ordemServico.get().getValor());
                var pagamentoID = pagamentoService.save(new PagamentoRequestDTO(pagamento));
                ordemServico.get().setPagamento_id(pagamentoID.getId());
                ordemServico.get().setStatusPagamento(StatusPagamento.PAGO);
                ordemServicoRepository.save(ordemServico.get());
            }else {
                throw new ObjectNotFoundException("A ordem com o id %d nao esta Finalizada.", id);
            }
            return new OrdemServicoDTO(ordemServico.get());
        } else {
            throw new ObjectNotFoundException("Ordem nao localizada para pagamento");
        }
    }


   /* public OrdemAprovacaoClienteDTO acompanharOrdem(String ordemLogin, String ordemSenha , AutorizarOrdemServico autorizarOrdemServico){


    } */


}
