package com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusOrdem;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class OrdemServicoRequestDTO {

    private Integer funcionario_id;
    private Integer cliente_id;

    public OrdemServicoRequestDTO() {
    }

    public OrdemServicoRequestDTO(Integer funcionario_id, Integer cliente_id) {
        this.funcionario_id = funcionario_id;
        this.cliente_id = cliente_id;
    }



    public Integer getFuncionario_id() {
        return funcionario_id;
    }

    public void setFuncionario_id(Integer funcionario_id) {
        this.funcionario_id = funcionario_id;
    }

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.cliente_id = cliente_id;
    }
}
