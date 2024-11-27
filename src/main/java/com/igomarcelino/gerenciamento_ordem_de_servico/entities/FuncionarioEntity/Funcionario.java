package com.igomarcelino.gerenciamento_ordem_de_servico.entities.FuncionarioEntity;

import com.igomarcelino.gerenciamento_ordem_de_servico.entities.ClienteEntity.Pessoa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Funcionario extends Pessoa {

    @NotBlank
    @Size(min = 6, max = 20, message = "O login deve ter entre 6 e 20 caracteres")
    @Column(nullable = false)
    private String usuarioLogin;

    @NotBlank
    @Size(min = 6, max = 20, message = "a senha deve ter entre 6 e 20 caracteres")
    @Column(nullable = false)
    private String senhaLogin;

    public Funcionario() {
    }

    public Funcionario(String usuarioLogin, String senhaLogin) {
        this.usuarioLogin = usuarioLogin;
        this.senhaLogin = senhaLogin;
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getSenhaLogin() {
        return senhaLogin;
    }

    public void setSenhaLogin(String senhaLogin) {
        this.senhaLogin = senhaLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Funcionario that)) return false;

        return getUsuarioLogin() != null ? getUsuarioLogin().equals(that.getUsuarioLogin()) : that.getUsuarioLogin() == null;
    }

    @Override
    public int hashCode() {
        return getUsuarioLogin() != null ? getUsuarioLogin().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "usuarioLogin='" + usuarioLogin + '\'' +
                ", senhaLogin='" + senhaLogin + '\'' +
                '}';
    }
}
