package com.igomarcelino.gerenciamento_ordem_de_servico.dto.PagamentoDTO;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.FormaPagamento;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Pagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PagamentoDTO {

    private Integer id;
    private FormaPagamento formaPagamento;
    private BigDecimal valor;
    private LocalDate data;

    public PagamentoDTO() {
    }

    public PagamentoDTO(PagamentoRequestDTO pagamentoRequestDTO){
        formaPagamento = pagamentoRequestDTO.getFormaPagamento();
        valor = pagamentoRequestDTO.getValor();
        data = LocalDate.now();
    }

    public PagamentoDTO(FormaPagamento formaPagamento, BigDecimal valor) {
        this.formaPagamento = formaPagamento;
        this.valor = valor;
        data = LocalDate.now();
    }

    public PagamentoDTO(Pagamento pagamento){
        BeanUtils.copyProperties(pagamento,this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
