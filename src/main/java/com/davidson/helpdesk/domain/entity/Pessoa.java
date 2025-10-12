package com.davidson.helpdesk.domain.entity;

import com.davidson.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public abstract class Pessoa implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id;
  protected String nome;

  @Column(unique = true)
  protected String cpf;

  @Column(unique = true)
  protected String email;
  protected String senha;

  @ElementCollection(fetch = FetchType.EAGER) // carrega os perfis junto com a pessoa
  @CollectionTable(name = "PERFIS") // nome da tabela que vai armazenar os perfis
  protected Set<Integer> perfis = new HashSet<>();

  @JsonFormat(pattern = "dd/MM/yyyy")
  protected LocalDate dataCriacao = LocalDate.now();

  public Pessoa() {
    super();
    addPerfil(Perfil.CLIENTE);
  }

  public Pessoa(Long id, String nome, String cpf, String email, String senha) {
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.email = email;
    this.senha = senha;
    addPerfil(Perfil.CLIENTE);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Pessoa pessoa = (Pessoa) o;
    return Objects.equals(id, pessoa.id) && Objects.equals(cpf, pessoa.cpf);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cpf);
  }
}
