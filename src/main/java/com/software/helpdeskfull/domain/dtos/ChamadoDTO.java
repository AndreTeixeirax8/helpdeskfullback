package com.software.helpdeskfull.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.software.helpdeskfull.domain.Chamado;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class ChamadoDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    private  Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")//definir formato da data
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")//definir formato da data
    private LocalDate dataFechamento = LocalDate.now();
    @NotNull(message = "O campo prioridade é requerido")
    private Integer prioridade;//aqui é um integer pois vai receber apenas o codigo da prioridade
    @NotNull(message = "O campo status é requerido")
    private Integer status;
    @NotNull(message = "O campo titulo é requerido")
    private String titulo;
    @NotNull(message = "O campo observações é requerido")
    private String observacoes;
    @NotNull(message = "O campo técnico é requerido")
    private Integer tecnico;//aqui é um integer pois vai receber apenas o codigo do tecnico
    @NotNull(message = "O campo cliente é requerido")
    private Integer cliente;//aqui é um integer pois vai receber apenas o codigo do cliente
    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO(){
        super();
    }

    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade().getCodigo();
        this.status = obj.getStatus().getCodigo();
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
        this.tecnico = obj.getTecnico().getId();
        this.cliente = obj.getCliente().getId();
        this.nomeTecnico = obj.getCliente().getNome();
        this.nomeCliente = obj.getTecnico().getNome();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
