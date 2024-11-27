package com.igomarcelino.gerenciamento_ordem_de_servico.entities.EnderecoEntity;

import com.igomarcelino.gerenciamento_ordem_de_servico.projection.EnderecoProjection;
import jakarta.persistence.*;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private Integer CEP;

    public Endereco() {
    }
    public Endereco(EnderecoProjection enderecoProjection) {
        id = enderecoProjection.getId();
        rua = enderecoProjection.getRua();
        numero = enderecoProjection.getNumero();
        complemento = enderecoProjection.getComplemento();
        bairro = enderecoProjection.getBairro();
        cidade = enderecoProjection.getCidade();
        estado = enderecoProjection.getEstado();
        CEP = enderecoProjection.getCEP();
    }

    public Endereco(String rua, String numero, String complemento, String bairro, String cidade, String estado, Integer CEP) {

        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.CEP = CEP;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCEP() {
        return CEP;
    }

    public void setCEP(Integer CEP) {
        this.CEP = CEP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco endereco)) return false;

        if (getRua() != null ? !getRua().equals(endereco.getRua()) : endereco.getRua() != null) return false;
        if (getNumero() != null ? !getNumero().equals(endereco.getNumero()) : endereco.getNumero() != null)
            return false;
        return getCEP() != null ? getCEP().equals(endereco.getCEP()) : endereco.getCEP() == null;
    }

    @Override
    public int hashCode() {
        int result = getRua() != null ? getRua().hashCode() : 0;
        result = 31 * result + (getNumero() != null ? getNumero().hashCode() : 0);
        result = 31 * result + (getCEP() != null ? getCEP().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", CEP=" + CEP +
                '}';
    }
}
