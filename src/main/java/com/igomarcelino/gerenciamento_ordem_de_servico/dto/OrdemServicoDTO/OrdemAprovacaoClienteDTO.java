package com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.AutorizarOrdemServico;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.OrdemServico;

import java.time.LocalDate;
import java.util.Random;

public class OrdemAprovacaoClienteDTO {

    private String ordemLogin;
    private String ordemSenha;


    public OrdemAprovacaoClienteDTO() {
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
