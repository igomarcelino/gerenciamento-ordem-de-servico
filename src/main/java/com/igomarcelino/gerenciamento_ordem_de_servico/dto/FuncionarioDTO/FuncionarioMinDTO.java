package com.igomarcelino.gerenciamento_ordem_de_servico.dto.FuncionarioDTO;

import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Funcionario;

public class FuncionarioMinDTO {

    private Integer id;
    private String nome;
    private String cpf;

    public FuncionarioMinDTO() {
    }

   /* public FuncionarioMinDTO(Funcionario funcionario) {
        id = funcionario.getId();
        nome = funcionario.getNome();
        cpf = funcionario.getCpf();
    }*/

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
}
