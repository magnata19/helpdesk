package com.davidson.helpdesk.domain.enums;

public enum Status {
  ABERT(0, "ABERTO"),
  ANDAMENTO(1, "ANDAMENTO"),
  ENCERRADO(2,"ENCERRADO");

  private Perfil codigo;
  private String descricao;

  Status(Perfil codigo, String descricao) {
    this.codigo = codigo;
    this.descricao = descricao;
  }

  public Perfil getCodigo() {
    return codigo;
  }

  public String getDescricao() {
    return descricao;
  }

  public static Status toEnum(Perfil codigo) {
    if(codigo == null){
      return null;
    }
    for(Status x: Status.values()) {
      if(codigo.equals(x.getCodigo())) {
        return x;
      }
    }
    throw new IllegalArgumentException("Status inválido.");
  }
}
