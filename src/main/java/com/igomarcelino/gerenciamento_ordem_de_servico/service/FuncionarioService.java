package com.igomarcelino.gerenciamento_ordem_de_servico.service;

import com.igomarcelino.gerenciamento_ordem_de_servico.components.PasswordCriptComponent;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.FuncionarioDTO.FuncionarioDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.FuncionarioDTO.FuncionarioMinDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Funcionario;
import com.igomarcelino.gerenciamento_ordem_de_servico.exceptions.ObjectNotFoundException;
import com.igomarcelino.gerenciamento_ordem_de_servico.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Autowired
    PasswordCriptComponent passwordCriptComponent;

    /**
     * Metodo que retorna todos funcionarios
     * */
    public List<FuncionarioMinDTO> findAll(){
        return funcionarioRepository.findAll().
                stream().
                map(FuncionarioMinDTO::new).
                toList();
    }

    /**
     * Metoto que retorna um Funcionario pelo ID
     * */
    public FuncionarioDTO findById(Integer id){
        return funcionarioRepository.findAll().
                stream().
                filter(funcionario -> funcionario.getId().equals(id)).
                map(FuncionarioDTO::new).
                findAny().
                orElseThrow(()-> new ObjectNotFoundException("ID %s nao localizado", id));
    }


    /**
     * Salva um funcionario
     * */
    public FuncionarioDTO save(FuncionarioDTO funcionarioDTO){
        var funcionario = new Funcionario(funcionarioDTO);
        // cria o processo de criptografia para o password antes de persistir no banco de dados
        String passwordEncrypted = passwordCriptComponent.passwordEncoder(funcionario.getSenhaLogin());
        funcionario.setSenhaLogin(passwordEncrypted);
        funcionarioRepository.save(funcionario);
        return new FuncionarioDTO(funcionario);
    }


    /**
     * Metodo para atualizar um Funcionario pelo ID
     * */

    public void update(Integer id, FuncionarioDTO funcionarioDTO){
        Optional<Funcionario> funcinonario = funcionarioRepository.findById(id);
        if (!funcinonario.isEmpty()) {
            funcinonario.get().setNome(funcionarioDTO.getNome());
            funcinonario.get().setEmail(funcionarioDTO.getEmail());
            funcinonario.get().setTelefone(funcionarioDTO.getTelefone());
            funcionarioRepository.save(funcinonario.get());
        }else {
            throw  new ObjectNotFoundException("Funcionario com o ID %d nao localizado", id);
        }
    }

    /**
     * Metodo para deletar um funcionario
     * */

    public void deleteByID(Integer id){
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        if (!funcionario.isEmpty()){
            funcionarioRepository.delete(funcionario.get());
        }else {
            throw new ObjectNotFoundException("Id %d nao localizado!", id);
        }
    }

    /**
     * Metodo procura pelo cpf
     * */
    public FuncionarioDTO findByCPF(String cpf){
        Optional<FuncionarioDTO> funcionarioDTO = funcionarioRepository.findByCPF(cpf).
                map(FuncionarioDTO::new);
        return funcionarioRepository.findByCPF(cpf).
                stream().
                map(FuncionarioDTO::new).
                findAny().
                orElseThrow(() -> new ObjectNotFoundException("Cpf: %s , nao localizado", cpf));

    }



    public Funcionario loadUserByUsername(String username) throws UsernameNotFoundException {
        var funcionario = funcionarioRepository.findByUsuarioLogin(username);
        return funcionario;
    }
}
