package com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO;

public class OrdemAcompanhamentoClienteDTO {

    private String ordemLogin;
    private String ordemSenha;


    public OrdemAcompanhamentoClienteDTO() {
    }

    public String getOrdemLogin() {
        return ordemLogin;
    }

    public void setOrdemLogin(String ordemLogin) {
        this.ordemLogin = ordemLogin;
    }

    public String getOrdemSenha() {
        return ordemSenha;
    }

    public void setOrdemSenha(String ordemSenha) {
        this.ordemSenha = ordemSenha;
    }


}
