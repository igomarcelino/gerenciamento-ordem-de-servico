package com.igomarcelino.gerenciamento_ordem_de_servico.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ServicoBelongingPK {

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "ordem_id")
    private OrdemServico ordemServico;

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public ServicoBelongingPK(Servico servico, OrdemServico ordemServico) {
        this.servico = servico;
        this.ordemServico = ordemServico;
    }

    public ServicoBelongingPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServicoBelongingPK that)) return false;

        if (getServico() != null ? !getServico().equals(that.getServico()) : that.getServico() != null) return false;
        return getOrdemServico() != null ? getOrdemServico().equals(that.getOrdemServico()) : that.getOrdemServico() == null;
    }

    @Override
    public int hashCode() {
        int result = getServico() != null ? getServico().hashCode() : 0;
        result = 31 * result + (getOrdemServico() != null ? getOrdemServico().hashCode() : 0);
        return result;
    }
}
