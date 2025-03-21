package com.igomarcelino.gerenciamento_ordem_de_servico.entities;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.FuncionarioDTO.FuncionarioDTO;
import jakarta.persistence.*;
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
public class Funcionario  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionario_id")
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50")
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String cpf;


    @NotBlank
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String telefone;


    @NotBlank
    @Size(min = 6, max = 20, message = "O login deve ter entre 6 e 20 caracteres")
    @Column(nullable = false,name = "usuario_login")
    private String usuarioLogin;

    @NotBlank
    @Size(min = 6, message = "a senha deve ter no minimo 6 caracteres")
    @Column(nullable = false,name = "senha_login",columnDefinition = "TEXT")
    private String senhaLogin;

    /**
     * Aqui estamos criando o relacionamento de muitos para muitos
     * referenciando as colunas id de funcionario e role, e criando um vinculo
     * para a tabela funcionario_role_lista
     * Como o projeto esta utilizando h2 console esse @JoinTable estara criando uma tabela
     * */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "funcionario_roles_list"
    ,joinColumns = @JoinColumn(name = "funcionario_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
    
}
