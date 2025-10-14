package com.davidson.helpdesk.domain.entity;

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

  public List<Chamado> getChamados() {
    return chamados;
  }

  public void setChamados(List<Chamado> chamados) {
    this.chamados = chamados;
  }
}
