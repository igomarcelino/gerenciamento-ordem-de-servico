package com.igomarcelino.gerenciamento_ordem_de_servico.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class ServicoBelonging {

    @EmbeddedId
    ServicoBelongingPK id = new ServicoBelongingPK();

    public ServicoBelonging() {
    }

    public ServicoBelongingPK getId() {
        return id;
    }

    public void setId(ServicoBelongingPK id) {
        this.id = id;
    }

    public ServicoBelonging(Servico servico, OrdemServico ordemServico) {
        id.setServico(servico);
        id.setOrdemServico(ordemServico);
    }

}
