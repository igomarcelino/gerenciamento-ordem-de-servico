package com.igomarcelino.gerenciamento_ordem_de_servico.dto.ClienteDTO;

import com.igomarcelino.gerenciamento_ordem_de_servico.entities.ClienteEntity.Cliente;

public class ClienteMinDTO {

    private Integer id;
    private String nome;
    private String cpf;

    public ClienteMinDTO() {
    }

    public ClienteMinDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClienteMinDTO that)) return false;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getNome() != null ? !getNome().equals(that.getNome()) : that.getNome() != null) return false;
        return getCpf() != null ? getCpf().equals(that.getCpf()) : that.getCpf() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getNome() != null ? getNome().hashCode() : 0);
        result = 31 * result + (getCpf() != null ? getCpf().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClienteMinDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
