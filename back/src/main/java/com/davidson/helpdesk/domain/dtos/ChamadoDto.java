package com.davidson.helpdesk.domain.dtos;

import com.davidson.helpdesk.domain.entity.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class ChamadoDto implements Serializable {

  private Long id;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataAbertura = LocalDate.now();

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataFechamento;

  @NotNull(message = "O campo PRIORIDADE é obrigatório.")
  private Integer prioridadeCod;
  @NotNull(message = "O campo STATUS é obrigatório.")
  private Integer statusCod;
  @NotNull(message = "O campo TÍTULO é obrigatório.")
  private String titulo;
  @NotNull(message = "O campo OBSERVAÇÕES é obrigatório.")
  private String observacoes;
  @NotNull(message = "O campo TÉCNICO é obrigatório.")
  private Long tecnicoId;
  @NotNull(message = "O campo CLIENTE é obrigatório.")
  private Long clienteId;
  private String nomeTecnico;
  private String nomeCliente;

  public ChamadoDto() {
    super();
  }


  public ChamadoDto(Chamado chamado) {
    this.id = chamado.getId();
    this.dataAbertura = chamado.getDataAbertura();
    this.dataFechamento = chamado.getDataFechamento();
    this.prioridadeCod = chamado.getPrioridade().getCodigo();
    this.statusCod = chamado.getStatus().getCodigo();
    this.titulo = chamado.getTitulo();
    this.observacoes = chamado.getObservacoes();
    this.tecnicoId = chamado.getTecnico().getId();
    this.clienteId = chamado.getCliente().getId();
    this.nomeTecnico = chamado.getTecnico().getNome();
    this.nomeCliente = chamado.getCliente().getNome();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  public Integer getPrioridadeCod() {
    return prioridadeCod;
  }

  public void setPrioridadeCod(Integer prioridadeCod) {
    this.prioridadeCod = prioridadeCod;
  }

  public Integer getStatusCod() {
    return statusCod;
  }

  public void setStatusCod(Integer statusCod) {
    this.statusCod = statusCod;
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

  public Long getTecnicoId() {
    return tecnicoId;
  }

  public void setTecnicoId(Long tecnicoId) {
    this.tecnicoId = tecnicoId;
  }

  public Long getClienteId() {
    return clienteId;
  }

  public void setClienteId(Long clienteId) {
    this.clienteId = clienteId;
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
