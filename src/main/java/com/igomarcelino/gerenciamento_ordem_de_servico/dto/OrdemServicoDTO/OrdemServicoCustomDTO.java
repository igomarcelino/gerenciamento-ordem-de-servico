package com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusOrdem;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Servico;

import java.math.BigDecimal;
import java.util.List;


public class OrdemServicoCustomDTO {

    private Integer id;
    private String nomeFuncionario;
    private StatusOrdem statusOrdem;
    private BigDecimal valor;
    private List<Servico> servicoList;

    // Construtor com todos os atributos
    public OrdemServicoCustomDTO(Integer id, String nomeFuncionario, StatusOrdem statusOrdem, BigDecimal valor, List<Servico> servicoList) {
        this.id = id;
        this.nomeFuncionario = nomeFuncionario;
        this.statusOrdem = statusOrdem;
        this.valor = valor;
        this.servicoList = servicoList;
    }

    // Construtor vazio
    public OrdemServicoCustomDTO() {
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
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

    public List<Servico> getServicoList() {
        return servicoList;
    }

    public void setServicoList(List<Servico> servicoList) {
        this.servicoList = servicoList;
    }

    // Builder
    public static class Builder {
        private Integer id;
        private String nomeFuncionario;
        private StatusOrdem statusOrdem;
        private BigDecimal valor;
        private List<Servico> servicoList;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder nomeFuncionario(String nomeFuncionario) {
            this.nomeFuncionario = nomeFuncionario;
            return this;
        }

        public Builder statusOrdem(StatusOrdem statusOrdem) {
            this.statusOrdem = statusOrdem;
            return this;
        }

        public Builder valor(BigDecimal valor) {
            this.valor = valor;
            return this;
        }

        public Builder servicoList(List<Servico> servicoList) {
            this.servicoList = servicoList;
            return this;
        }

        public OrdemServicoCustomDTO build() {
            return new OrdemServicoCustomDTO(id, nomeFuncionario, statusOrdem, valor, servicoList);
        }
    }

    // Método estático para iniciar o builder
    public static Builder builder() {
        return new Builder();
    }
}
