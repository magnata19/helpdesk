package com.davidson.helpdesk.domain.entity;

import com.davidson.helpdesk.domain.dtos.TecnicoDto;
import com.davidson.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Tecnico extends Pessoa {

  @OneToMany(mappedBy = "tecnico")
  @JsonIgnore
  private List<Chamado> chamados = new ArrayList<>(); // iniciar a lista vazia e evita nullpointerexception

  public Tecnico() {
    super();
    addPerfil(Perfil.CLIENTE);
  }

  public Tecnico(Long id, String nome, String cpf, String email, String senha) {
    super(id, nome, cpf, email, senha);
    addPerfil(Perfil.CLIENTE);
  }

  public Tecnico(TecnicoDto obj) {
    this.id = obj.getId();
    this.nome = obj.getNome();
    this.cpf = obj.getCpf();
    this.email = obj.getEmail();
    this.senha = obj.getSenha();
    this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
    this.dataCriacao = obj.getDataCriacao();
  }

  public List<Chamado> getChamados() {
    return chamados;
  }

  public void setChamados(List<Chamado> chamados) {
    this.chamados = chamados;
  }
}
