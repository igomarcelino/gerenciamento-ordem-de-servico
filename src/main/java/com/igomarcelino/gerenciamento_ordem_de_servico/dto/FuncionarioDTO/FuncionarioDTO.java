package com.igomarcelino.gerenciamento_ordem_de_servico.dto.FuncionarioDTO;

import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Funcionario;
import com.igomarcelino.gerenciamento_ordem_de_servico.projection.FuncionarioProjection;
import org.springframework.beans.BeanUtils;

public class FuncionarioDTO {
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String usuarioLogin;
    private String senhaLogin;

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Funcionario funcionario){
        BeanUtils.copyProperties(funcionario, this);
        senhaLogin = "********";
    }

    public FuncionarioDTO(FuncionarioProjection funcionarioProjection) {
        id = funcionarioProjection.getId();
        nome= funcionarioProjection.getNome();
        cpf = funcionarioProjection.getCpf();
        telefone = funcionarioProjection.getTelefone();
        email = funcionarioProjection.getEmail();
        usuarioLogin = funcionarioProjection.getUsuarioLogin();
        senhaLogin = "*******";

    }

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
        if (!(o instanceof FuncionarioDTO that)) return false;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getCpf() != null ? !getCpf().equals(that.getCpf()) : that.getCpf() != null) return false;
        return getUsuarioLogin() != null ? getUsuarioLogin().equals(that.getUsuarioLogin()) : that.getUsuarioLogin() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCpf() != null ? getCpf().hashCode() : 0);
        result = 31 * result + (getUsuarioLogin() != null ? getUsuarioLogin().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FuncionarioDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", usuarioLogin='" + usuarioLogin + '\'' +
                ", senhaLogin='" + senhaLogin + '\'' +
                '}';
    }
}
