package com.davidson.helpdesk.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class Tecnico extends Pessoa {

  private List<Chamado> chamados = new ArrayList<>(); // iniciar a lista vazia e evita nullpointerexception

  public Tecnico() {
    super();
  }

  public Tecnico(Long id, String nome, String cpf, String email, String senha) {
    super(id, nome, cpf, email, senha);
  }

  public List<Chamado> getChamados() {
    return chamados;
  }

  public void setChamados(List<Chamado> chamados) {
    this.chamados = chamados;
  }
}
