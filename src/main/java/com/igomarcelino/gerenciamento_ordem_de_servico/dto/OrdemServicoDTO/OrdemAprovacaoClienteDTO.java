package com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.AutorizarOrdemServico;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.OrdemServico;

import java.time.LocalDate;
import java.util.Random;

public class OrdemAprovacaoClienteDTO {

    private Integer id;
    private String ordemLogin;
    private String ordemSenha;
    private AutorizarOrdemServico autorizarOrdemServico;

    public OrdemAprovacaoClienteDTO() {
    }

    public OrdemAprovacaoClienteDTO(OrdemServico ordemServico) {
        this.id = ordemServico.getId();
        ordemLogin = ordemServico.getOrdemLogin() ;
        ordemSenha = ordemServico.getOrdemSenha();
        this.autorizarOrdemServico = ordemServico.getAutorizarOrdemServico();
    }

    public String gerarOsId(OrdemServicoDTO ordemServicoDTO){
        LocalDate data = LocalDate.now();
        String dataOrdem = String.valueOf(data);
        String clienteId = String.valueOf(ordemServicoDTO.getCliente_id());
        String ordemId = String.valueOf(ordemServicoDTO.getId());

        return new StringBuilder(dataOrdem+clienteId+ordemId).toString();
    }

    public String gerarSenha(){
        int  senha = new Random().nextInt(10000);
         return new StringBuilder(senha).toString();
    }
}
