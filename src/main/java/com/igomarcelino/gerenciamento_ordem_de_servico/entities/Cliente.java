package com.igomarcelino.gerenciamento_ordem_de_servico.entities;

import jakarta.persistence.*;

@Entity
public class Cliente extends Pessoa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    public Cliente() {
    }

    public Cliente(Integer id, String nome, String cpf, String telefone, String email, Endereco endereco) {
        super(id, nome, cpf, telefone, email);
        this.endereco = endereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "endereco=" + endereco +
                '}';
    }
}
