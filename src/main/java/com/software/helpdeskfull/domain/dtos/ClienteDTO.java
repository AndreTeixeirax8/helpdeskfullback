package com.software.helpdeskfull.domain.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.software.helpdeskfull.domain.Cliente;
import com.software.helpdeskfull.domain.enums.Perfil;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
/*DTO serve para uma boa pratica de segurança por que não podemos dar acesso
* total a nossas entidades(Tabela no banco) por isso usamos o padrão DTO e ai devolvemos apenas as informações
* necessarias deixando assim nossa entidade(Tabela no banco) protegida.
* DTO siguinifica Objeto de Transferencia de Dados */

public class ClienteDTO implements Serializable {
    private static final long serialVersionUID=1L;

    protected Integer id;
    @NotNull(message = "O Campo nome é obrigatório")
    protected String  nome;
    @NotNull(message = "O Campo cpf é obrigatório")
    protected String  cpf;
    @NotNull(message = "O Campo email é obrigatório")
    protected String  email;
    @NotNull(message = "O Campo senha é obrigatório")
    protected String  senha;


    protected Set<Integer> perfis = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")//definir formato da data
    protected LocalDate dataCriacao =  LocalDate.now(); //Recebe um localDate que é a data local do momento de

    public ClienteDTO(){
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public ClienteDTO(Cliente obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x-> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
        addPerfil(Perfil.CLIENTE);
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        //mapeamento para trazer do banco o nome do perfil e não só o codigo
        return perfis.stream().map(x-> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
