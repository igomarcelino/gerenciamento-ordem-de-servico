package com.igomarcelino.gerenciamento_ordem_de_servico.dto.OrdemServicoDTO;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusOrdem;
import com.igomarcelino.gerenciamento_ordem_de_servico.projection.OrdemServicoProjection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

public class OrdemServicoRequestDTO {

    private Integer funcionario_id;
    private Integer cliente_id;

    private StatusOrdem statusOrdem;

    public OrdemServicoRequestDTO() {
    }

    public OrdemServicoRequestDTO(Integer funcionario_id, Integer cliente_id) {
        this.funcionario_id = funcionario_id;
        this.cliente_id = cliente_id;
    }

    public OrdemServicoRequestDTO(OrdemServicoProjection ordemServicoProjection) {
        funcionario_id = ordemServicoProjection.getFuncionario_id();
        cliente_id = ordemServicoProjection.getCliente_id();
        statusOrdem = ordemServicoProjection.getStatusOrdem();

    }


    public StatusOrdem getStatusOrdem() {
        return statusOrdem;
    }

    public void setStatusOrdem(StatusOrdem statusOrdem) {
        this.statusOrdem = statusOrdem;
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
