package com.software.helpdeskfull.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.software.helpdeskfull.domain.dtos.ClienteDTO;
import com.software.helpdeskfull.domain.dtos.TecnicoDTO;
import com.software.helpdeskfull.domain.enums.Perfil;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Cliente extends Pessoa implements Serializable {
    private static final long serialVersionUID=1L;

    @JsonIgnore//notação para iguinorar esse campo e proteger da serialização
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados =  new ArrayList<>();

    public Cliente() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    //construtor que sera usado no clienteservice
    public Cliente(ClienteDTO obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x-> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
