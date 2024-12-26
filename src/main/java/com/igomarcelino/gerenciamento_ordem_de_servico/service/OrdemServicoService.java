package com.igomarcelino.gerenciamento_ordem_de_servico.service;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.AutorizarOrdemServico;
import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.FormaPagamento;
import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusOrdem;
import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusPagamento;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoCustomDTO;
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
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.FuncionarioRepository;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.OrdemServicoRepository;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.ServicoBelongingRepository;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
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
    ServicoRepository servicoRepository;
    @Autowired
    PagamentoService pagamentoService;
    @Autowired
    FuncionarioRepository funcionarioRepository;

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
    public OrdemServicoCustomDTO findById(Integer id) {
        var osRecuperada = ordemServicoRepository.findById(id).
                orElseThrow(() -> new ObjectNotFoundException("Ordem %d nao localizada", id));
        // se existir a ordem preencher a lista de servico da ordem
        List<Servico> servicoList = getServicosPorOrdemId(osRecuperada.getId());
        // busca o nome do funcionario da ordem
        String nomeFuncionario = getNomeFuncionario(osRecuperada.getFuncionario_id());

        // atribui os valores ao dto customizado utilizando um builder
        return OrdemServicoCustomDTO.builder().
                id(osRecuperada.getId()).
                nomeFuncionario(nomeFuncionario).
                statusOrdem(osRecuperada.getStatusOrdem()).
                valor(osRecuperada.getValor()).
                servicoList(servicoList).build();
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
                orElseThrow(() -> new ObjectNotFoundException("Status %s nao possui ordens", statusOrdem.name().replace("_", " ")));

        return ordemPorStatus.stream().map(OrdemServicoRequestDTO::new).toList();
    }

    /**
     * Ordens por Cliente, procura pelo cpf
     */
    @Transactional(readOnly = true)
    public List<OrdemServicoResumeDTO> ordemPorCliente(String cpf) {
        var ordensPorCliente = ordemServicoRepository.findByCpfCliente(cpf).orElseThrow(() -> new ObjectNotFoundException("Nao possui ordens em aberto"));

        if (ordensPorCliente.isEmpty()) {
            throw new ObjectNotFoundException("Nao possui ordens");
        }
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
    public OrdemServicoCustomDTO autorizarOrdem(String ordemLogin, String ordemSenha, AutorizarOrdemServico autorizarOrdemServico) {

        var osRecuperada = ordemServicoRepository.findByOrdemLogin(ordemLogin, ordemSenha).
                orElseThrow(() -> new ObjectNotFoundException("Ordem %s nao localizada", ordemLogin));
        // se existir a ordem preencher a lista de servico da ordem
        List<Servico> servicoList = getServicosPorOrdemId(osRecuperada.getId());
        // busca o nome do funcionario da ordem
        String nomeFuncionario = getNomeFuncionario(osRecuperada.getFuncionario_id());

        if (autorizarOrdemServico == AutorizarOrdemServico.APROVAR) {
            osRecuperada.setStatusOrdem(StatusOrdem.APROVADA_PELO_CLIENTE);
        } else if (autorizarOrdemServico == AutorizarOrdemServico.REPROVAR) {
            osRecuperada.setStatusOrdem(StatusOrdem.ORCAMENTO_REPROVADO);
        }
        ordemServicoRepository.save(osRecuperada);

        return OrdemServicoCustomDTO.builder().
                id(osRecuperada.getId()).
                nomeFuncionario(nomeFuncionario).
                statusOrdem(osRecuperada.getStatusOrdem()).
                valor(osRecuperada.getValor()).
                servicoList(servicoList).build();
    }

    /**
     * Retorna o status da ordem de servico pelo id e senha
     */

    @Transactional
    public OrdemServicoCustomDTO acompanharStatusOrdem(String ordemLogin, String ordemSenha) {
        var osRecuperada = ordemServicoRepository.findByOrdemLogin(ordemLogin, ordemSenha).
                orElseThrow(() -> new ObjectNotFoundException("Ordem %s nao localizada", ordemLogin));
        List<Servico> servicos = getServicosPorOrdemId(osRecuperada.getId());
        String nomeFuncionario = getNomeFuncionario(osRecuperada.getFuncionario_id());

        return OrdemServicoCustomDTO.builder().
                id(osRecuperada.getId()).
                nomeFuncionario(nomeFuncionario).
                statusOrdem(osRecuperada.getStatusOrdem()).
                valor(osRecuperada.getValor()).
                servicoList(servicos).build();
    }

    /**
     * Total a receber por periodo
     */
    @Transactional(readOnly = true)
    public BigDecimal valoresAPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        var ordensPorPeriodo = ordemServicoRepository.findAll();

        return ordensPorPeriodo.stream().filter(ordem ->
                        ordem.getVencimento().isAfter(dataInicio) && ordem.getVencimento().isBefore(dataFim)). // verifica a data de vencimento
                filter(ordemServico -> ordemServico.getStatusPagamento().equals(StatusPagamento.NAO_PAGO)). // verifica se a ordem ja nao foi paga
                filter(ordemServico -> ordemServico.getValor() != null). // verifica se nao a ordem com valor nulo
                map(ordemServico -> ordemServico.getValor()). // transforma em um stream de BigDecimal
                reduce(BigDecimal.ZERO, BigDecimal::add).round(MathContext.DECIMAL32); // retorna um BigDecimal com arredondamento de casa decimal
    }
    /**
     * Retorna um total recebido por periodo
     * */
    @Transactional(readOnly = true)
    public BigDecimal valoresRecebidos(LocalDate dataInicio, LocalDate dataFim) {
        var ordensPorPeriodo = ordemServicoRepository.findAll();

        return ordensPorPeriodo.stream().filter(ordem ->
                ordem.getVencimento().isAfter(dataInicio) && ordem.getVencimento().isBefore(dataFim)). // verifica a data de vencimento
                filter(ordemServico -> ordemServico.getStatusPagamento().equals(StatusPagamento.PAGO)). // verifica se a ordem ja nao foi paga
                filter(ordemServico -> ordemServico.getValor() != null). // verifica se nao a ordem com valor nulo
                map(ordemServico -> ordemServico.getValor()). // transforma em um stream de BigDecimal
                reduce(BigDecimal.ZERO, BigDecimal::add).round(MathContext.DECIMAL32); // retorna um BigDecimal com arredondamento de casa decimal
    }

    /**
     * Metodos para reaproveitamento no codigo
     * Recuperar a lista de serviccos
     * Recuperar o nome do funcionario presente na ordem
     */
    private List<Servico> getServicosPorOrdemId(Integer ordemId) {
        return servicoRepository.findByOrdemId(ordemId)
                .orElseThrow(() -> new ObjectNotFoundException("Nao possui servicos."));
    }

    private String getNomeFuncionario(Integer funcionarioId) {
        return funcionarioRepository.nomeFuncionario(funcionarioId);
    }


}
