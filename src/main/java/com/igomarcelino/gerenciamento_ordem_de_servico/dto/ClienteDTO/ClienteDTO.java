package com.igomarcelino.gerenciamento_ordem_de_servico.dto.ClienteDTO;

import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Cliente;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Endereco;
import com.igomarcelino.gerenciamento_ordem_de_servico.projection.ClienteProjection;
import org.springframework.beans.BeanUtils;

public class ClienteDTO {

    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Endereco endereco;

    public ClienteDTO() {
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public ClienteDTO(ClienteProjection clienteProjection) {
        id = clienteProjection.getId();
        nome = clienteProjection.getNome();
        cpf = clienteProjection.getCpf();
        telefone = clienteProjection.getTelefone();
        email = clienteProjection.getEmail();

    }

    /**
     * atribui as propriedades  do cliente ao DTO
     * */
    public ClienteDTO (Cliente cliente){
        BeanUtils.copyProperties(cliente,this);
    }

    public ClienteDTO (ClienteDTO clienteDTO){
        BeanUtils.copyProperties(clienteDTO,this);
    }
}
