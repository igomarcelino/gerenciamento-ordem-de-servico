package com.igomarcelino.gerenciamento_ordem_de_servico.dto.ClienteDTO;

import com.igomarcelino.gerenciamento_ordem_de_servico.entities.EnderecoEntity.Endereco;
/**
 * Classe utilizada para atualizar Cliente Caso Exista
 * */
public class ClienteUpdateDTO {

    private String nome;
    private String telefone;
    private String email;
    private Endereco endereco;

    public ClienteUpdateDTO(String nome, String telefone, String email, Endereco endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
