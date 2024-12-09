package com.igomarcelino.gerenciamento_ordem_de_servico.entities;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusOrdem;
import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusPagamento;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO.OrdemServicoRequestDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer funcionario_id;
    private Integer cliente_id;
    private Integer pagamento_id;
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private StatusOrdem statusOrdem;
    private LocalDate vencimento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    public OrdemServico() {
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public OrdemServico(Integer id, Integer funcionario_id, Integer cliente_id) {
        this.id = id;
        this.funcionario_id = funcionario_id;
        this.cliente_id = cliente_id;
        this.pagamento_id = 0;
        this.statusOrdem = StatusOrdem.ABERTA ;


    }

    public OrdemServico(OrdemServicoRequestDTO ordemServicoRequestDTO){

        funcionario_id = ordemServicoRequestDTO.getFuncionario_id();
        cliente_id = ordemServicoRequestDTO.getCliente_id();
        pagamento_id = 0;
        statusOrdem = StatusOrdem.ABERTA;
        statusPagamento = StatusPagamento.NAO_PAGO;

    }

    public static OrdemServico verificaStatusOrdem(OrdemServico ordemServico){
        if (ordemServico.statusOrdem == StatusOrdem.FINALIZADA){
            ordemServico.setVencimento(LocalDate.now().plusDays(5));
        }
        return ordemServico;
    }

    public OrdemServico(Integer id, Integer funcionario_id, Integer cliente_id, Integer pagamento_id, BigDecimal valor, StatusOrdem statusOrdem, LocalDate vencimento, StatusPagamento statusPagamento) {
        this.id = id;
        this.funcionario_id = funcionario_id;
        this.cliente_id = cliente_id;
        this.pagamento_id = pagamento_id;
        this.valor = valor;
        this.statusOrdem = statusOrdem;
        this.vencimento = vencimento;
        this.statusPagamento = statusPagamento;
    }



    public OrdemServico(OrdemServicoDTO ordemServicoDTO) {
        BeanUtils.copyProperties(ordemServicoDTO,this);
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrdemServico that)) return false;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
