package com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusOrdem;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.OrdemServico;
import com.igomarcelino.gerenciamento_ordem_de_servico.projection.OrdemServicoProjection;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;


public class OrdemServicoDTO {
    private Integer id;
    private Integer funcionario_id;
    private Integer cliente_id;
    private Integer pagamento_id;
    private StatusOrdem statusOrdem;
    private BigDecimal valor;
    private LocalDate vencimento;

    public OrdemServicoDTO(OrdemServicoProjection ordemServicoProjection) {
         setId(ordemServicoProjection.getId());
         setStatusOrdem(ordemServicoProjection.getStatusOrdem());
         setValor(ordemServicoProjection.getValor());
         setVencimento(ordemServicoProjection.getVencimento());
         setPagamento_id(ordemServicoProjection.getPagamento_id());
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public OrdemServicoDTO() {
    }

    public OrdemServicoDTO(Integer id, Integer funcionario_id, Integer cliente_id, Integer pagamento_id, StatusOrdem statusOrdem) {
        this.id = id;
        this.funcionario_id = funcionario_id;
        this.cliente_id = cliente_id;
        this.pagamento_id = pagamento_id;
        this.statusOrdem = statusOrdem;
        valor = null;

    }

    public OrdemServicoDTO(OrdemServico ordemServico){
        BeanUtils.copyProperties(ordemServico, this);
    }

    public OrdemServicoDTO(OrdemServicoRequestDTO ordemServicoRequestDTO){
        funcionario_id = ordemServicoRequestDTO.getFuncionario_id();
        cliente_id = ordemServicoRequestDTO.getCliente_id();
        pagamento_id = 0;
        statusOrdem = StatusOrdem.ABERTA;


    }
    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFuncionario_id() {
        return funcionario_id;
    }

    public void setFuncionario_id(Integer funcionario_id) {
        this.funcionario_id = funcionario_id;
    }

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Integer getPagamento_id() {
        return pagamento_id;
    }

    public void setPagamento_id(Integer pagamento_id) {
        this.pagamento_id = pagamento_id;
    }

    public StatusOrdem getStatusOrdem() {
        return statusOrdem;
    }

    public void setStatusOrdem(StatusOrdem statusOrdem) {
        this.statusOrdem = statusOrdem;
    }

}
