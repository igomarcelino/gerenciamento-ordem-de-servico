package com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusOrdem;
import com.igomarcelino.gerenciamento_ordem_de_servico.projection.OrdemServicoProjection;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrdemServicoResumeDTO {
    private Integer id;
    private Integer pagamento_id;
    private StatusOrdem statusOrdem;
    private BigDecimal valor;
    private LocalDate vencimento;

    public OrdemServicoResumeDTO() {
    }

    public OrdemServicoResumeDTO(Integer id, Integer pagamento_id, StatusOrdem statusOrdem, BigDecimal valor, LocalDate vencimento) {
        this.id = id;
        this.pagamento_id = pagamento_id;
        this.statusOrdem = statusOrdem;
        this.valor = valor;
        this.vencimento = vencimento;
    }
    public OrdemServicoResumeDTO(OrdemServicoProjection ordemServicoProjection){
        setId(ordemServicoProjection.getId());
        setPagamento_id(ordemServicoProjection.getPagamento_id());
        setStatusOrdem(ordemServicoProjection.getStatusOrdem());
        setValor(ordemServicoProjection.getValor());
        setVencimento(ordemServicoProjection.getVencimento());

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }
}
