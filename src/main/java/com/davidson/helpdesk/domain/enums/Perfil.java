package com.davidson.helpdesk.domain.enums;

public enum Perfil {
  ADMIN(0,"ROLE_ADMIN"),
  CLIENTE(1,"ROLE_CLIENTE"),
  TECNICO(2,"ROLE_TECNICO");

  private java.lang.Integer codigo;
  private String descricao;

  Perfil(java.lang.Integer codigo, String descricao) {
    this.codigo = codigo;
    this.descricao = descricao;
  }

  public java.lang.Integer getCodigo() {
    return codigo;
  }

  public String getDescricao() {
    return descricao;
  }

  public static Perfil toEnum(java.lang.Integer codigo) {
    if(codigo == null) {
      return null;
    }

    for(Perfil x: Perfil.values()){
      if (codigo.equals(x.getCodigo())) {
        return x;
      }
    }
    throw new IllegalArgumentException("Perfil inválido.");
  }
}
