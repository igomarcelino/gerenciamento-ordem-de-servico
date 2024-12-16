package com.igomarcelino.gerenciamento_ordem_de_servico.service;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.AutorizarOrdemServico;
import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.FormaPagamento;
import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusOrdem;
import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusPagamento;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoRequestDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoResumeDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.PagamentoDTO.PagamentoRequestDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.OrdemServico;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Pagamento;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Servico;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.ServicoBelonging;
import com.igomarcelino.gerenciamento_ordem_de_servico.exceptions.BadReqException;
import com.igomarcelino.gerenciamento_ordem_de_servico.exceptions.DataAlreadyExistsException;
import com.igomarcelino.gerenciamento_ordem_de_servico.exceptions.ObjectNotFoundException;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.OrdemServicoRepository;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.ServicoBelongingRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     */
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

    @Transactional(readOnly = true)
    public List<OrdemServicoDTO> findAll() {
        return ordemServicoRepository.findAll().
                stream().
                map(OrdemServicoDTO::new).
                toList();
    }

    /**
     * Localizar pelo ID
     */
    @Transactional(readOnly = true)
    public OrdemServicoDTO findById(Integer id) {
        return ordemServicoRepository.findById(id).map(OrdemServicoDTO::new).get();
    }

    /**
     * Finalizar Ordem de servico
     */
    @Transactional
    public OrdemServicoDTO finalizarOrdem(Integer id, StatusOrdem statusOrdem) {
        var ordemServico = ordemServicoRepository.findById(id).
                orElseThrow(() -> new ObjectNotFoundException("Ordem %d nao localizada", id));
        var ordemAtualizada = new OrdemServico();

        if (ordemServico.getStatusOrdem() == StatusOrdem.FINALIZADA) {
            throw new DataAlreadyExistsException("A ordem com o id %d ja se encontra finalizada", id);
        }
        if (ordemServico.getStatusOrdem() != StatusOrdem.APROVADA_PELO_CLIENTE) {
            throw new BadReqException("Ordem %d nao aprovada pelo cliente", id);
        }
        ordemServico.setStatusOrdem(statusOrdem);
        ordemAtualizada = OrdemServico.verificaStatusOrdem(ordemServico);
        ordemServicoRepository.save(ordemAtualizada);


        return new OrdemServicoDTO(ordemAtualizada);
    }

    /**
     * Ordens por status
     */
    @Transactional(readOnly = true)
    public List<OrdemServicoRequestDTO> ordensPorStatus(StatusOrdem statusOrdem) {
        var ordemPorStatus = ordemServicoRepository.findByStatus(statusOrdem.name()).
                orElseThrow(()-> new ObjectNotFoundException("Status %s nao possui ordens", statusOrdem.name().replace("_", " ")));

        return ordemPorStatus.stream().map(OrdemServicoRequestDTO::new).toList();
    }

    /**
     * Ordens por Cliente, procura pelo cpf
     */
    @Transactional(readOnly = true)
    public List<OrdemServicoResumeDTO> ordemPorCliente(String cpf) {
        var ordensPorCliente = ordemServicoRepository.findByCpfCliente(cpf).orElseThrow(()-> new ObjectNotFoundException("Nao possui ordens em aberto"));

            return ordensPorCliente.stream().map(OrdemServicoResumeDTO::new).toList();

    }

    /**
     * Realiza o pagamento da ordem
     */
    //TODO - melhorar a logica para finalizar o pagamento, e melhorar o retorno dessa ordem com campos null
    @Transactional
    public OrdemServicoDTO realizarPagamento(FormaPagamento formaPagamento, Integer id) {
        var ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Ordem %d nao localizada", id));

        if (ordemServico.getStatusOrdem() != StatusOrdem.FINALIZADA) {
            throw new BadReqException("Ordem %d nao esta finalizada", id);
        }

        var pagamento = new Pagamento();
        pagamento.setFormaPagamento(formaPagamento);
        pagamento.setValor(ordemServico.getValor());
        var pagamentoID = pagamentoService.save(new PagamentoRequestDTO(pagamento));
        ordemServico.setPagamento_id(pagamentoID.getId());
        ordemServico.setStatusPagamento(StatusPagamento.PAGO);
        ordemServicoRepository.save(ordemServico);

        return new OrdemServicoDTO(ordemServico);
    }

    /**
     * Metodo utilizado para autorizar o processo de uma ordem de servico via id e senha da ordem
     */
    @Transactional
    public OrdemServicoDTO autorizarOrdem(String ordemLogin, String ordemSenha, AutorizarOrdemServico autorizarOrdemServico) {

        var ordemServico = ordemServicoRepository.findByOrdemLogin(ordemLogin, ordemSenha).
                orElseThrow(()-> new ObjectNotFoundException("Ordem %s nao localizada", ordemLogin));

        if (autorizarOrdemServico == AutorizarOrdemServico.APROVAR) {
            ordemServico.setStatusOrdem(StatusOrdem.APROVADA_PELO_CLIENTE);
        } else if (autorizarOrdemServico == AutorizarOrdemServico.REPROVAR) {
            ordemServico.setStatusOrdem(StatusOrdem.ORCAMENTO_REPROVADO);
        }
        ordemServicoRepository.save(ordemServico);

        return new OrdemServicoDTO(ordemServico);

    }

    /**
     * Retorna o status da ordem de servico pelo id e senha
     * */
    public OrdemServicoDTO acompanharStatusOrdem(String ordemLogin, String ordemSenha) {
               var ordemParaAcompanhar =  ordemServicoRepository.findByOrdemLogin(ordemLogin, ordemSenha).
                       orElseThrow(()-> new ObjectNotFoundException("Ordem %s nao localizada", ordemLogin));

               return new OrdemServicoDTO(ordemParaAcompanhar);
    }


}
