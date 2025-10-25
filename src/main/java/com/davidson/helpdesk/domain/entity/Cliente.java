package com.davidson.helpdesk.domain.entity;

import com.davidson.helpdesk.domain.dtos.ClienteDto;
import com.davidson.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Pessoa {

  @OneToMany(mappedBy = "cliente")
  @JsonIgnore
  private List<Chamado> chamados = new ArrayList<>(); // iniciar a lista vazia e evita nullpointerexception

  public Cliente() {
    super();
    addPerfil(Perfil.CLIENTE);
  }

  public Cliente(Long id, String nome, String cpf, String email, String senha) {
    super(id, nome, cpf, email, senha);
    addPerfil(Perfil.CLIENTE);
  }

  public Cliente(ClienteDto dto) {
    this.id = dto.getId();
    this.nome = dto.getNome();
    this.cpf = dto.getCpf();
    this.email = dto.getEmail();
    this.senha = dto.getSenha();
    this.perfis = dto.getPerfis().stream().map(x -> x.getCodigo()).collect(java.util.stream.Collectors.toSet());
    this.dataCriacao = dto.getDataCriacao();
  }

  public List<Chamado> getChamados() {
    return chamados;
  }

  public void setChamados(List<Chamado> chamados) {
    this.chamados = chamados;
  }
}
