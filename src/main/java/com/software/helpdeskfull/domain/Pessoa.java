package com.software.helpdeskfull.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.software.helpdeskfull.domain.enums.Perfil;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity //Notação para criar tabela no banco
public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID=1L;

    /*Os Atributos não podem ser do tipo private porque se for apenas essa classe vai
     acessar então deixamos ele como protecd pois ai as classes herdadas vão reconhecer
      isso é o conceito de herança
      */

    @Id //chave primaria no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gerar valor de forma automatica
    protected Integer id;
    protected String  nome;
    @Column(unique = true)//para não ter cpf duplicado
    protected String  cpf;
    @Column(unique = true)//para não ter cpf duplicado
    protected String  email;
    protected String  senha;

    /*Criado uma lista de perfil do enums e já iniciado
     com o HashSet para evitar erro de ponteiro null*/
    @ElementCollection(fetch = FetchType.EAGER) /*Para asegurar que a lista de elelemntos venha
    com os nomes alem do código*/
    @CollectionTable(name = "PERFIS")//Cria a tabela perfis
    protected Set<Integer> perfis = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")//definir formato da data
    protected LocalDate dataCriacao =  LocalDate.now(); //Recebe um localDate que é a data local do momento de

    public Pessoa(){
        super();
        /* aqui o usuario criado vai receber perfil de cliente*/
        addPerfil(Perfil.CLIENTE);
    }
    //Nesse construtor entra só os campos que vão receber valores do usuario
    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        /* aqui o usuario criado vai receber perfil de cliente*/
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
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
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

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result =  prime * result +((cpf == null)? 0 : cpf.hashCode());
        result =  prime * result +((id == null)? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Pessoa other = (Pessoa) obj;
        if(cpf == null){
            if(other.cpf != null)
                return false;
        }else if (!cpf.equals(other.cpf))
            return false;
        if(id == null){
            if(other.id != null)
                return false;
        }else if (!id.equals(other.id))
            return false;
        return true;
    }
}
