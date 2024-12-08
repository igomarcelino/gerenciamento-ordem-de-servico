package com.igomarcelino.gerenciamento_ordem_de_servico.entities;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.FuncionarioDTO.FuncionarioDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Funcionario extends Pessoa implements UserDetails {

    @NotBlank
    @Size(min = 6, max = 20, message = "O login deve ter entre 6 e 20 caracteres")
    @Column(nullable = false,name = "usuario_login")
    private String usuarioLogin;

    @NotBlank
    @Size(min = 6, max = 20, message = "a senha deve ter entre 6 e 20 caracteres")
    @Column(nullable = false,name = "senha_login",columnDefinition = "TEXT")
    private String senhaLogin;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Roles> rolesList;

    public Funcionario() {
    }

    public Funcionario(String usuarioLogin, String senhaLogin) {
        this.usuarioLogin = usuarioLogin;
        this.senhaLogin = senhaLogin;
    }

    public Funcionario(FuncionarioDTO funcionarioDTO){
        BeanUtils.copyProperties(funcionarioDTO,this);
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }
    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        for (Roles roles : rolesList){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roles.getRoleName());
            grantedAuthorityList.add(simpleGrantedAuthority);
        }

        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return senhaLogin;
    }

    @Override
    public String getUsername() {
        return usuarioLogin;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
