package com.igomarcelino.gerenciamento_ordem_de_servico.entities;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.FormaPagamento;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.PagamentoDTO.PagamentoDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.PagamentoDTO.PagamentoRequestDTO;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaPagamento formaPagamento;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate data;

    public Pagamento() {
    }

    public Pagamento(Integer id, FormaPagamento formaPagamento, BigDecimal valor, LocalDate data) {
        this.id = id;
        this.formaPagamento = formaPagamento;
        this.valor = valor;
        this.data = data;
    }


    public Pagamento(PagamentoDTO pagamentoDTO) {
        BeanUtils.copyProperties(pagamentoDTO,this);
    }

    public Pagamento(PagamentoRequestDTO pagamentoRequestDTO){
        formaPagamento = pagamentoRequestDTO.getFormaPagamento();
        valor = pagamentoRequestDTO.getValor();
        data = LocalDate.now();
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
