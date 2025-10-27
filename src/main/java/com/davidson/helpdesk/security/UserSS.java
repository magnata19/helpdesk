package com.davidson.helpdesk.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.davidson.helpdesk.domain.enums.Perfil;

public class UserSS implements UserDetails {

  private Long id;
  private String email;
  private String senha;
  private Collection<? extends GrantedAuthority> authorities;

  public UserSS(Long id, String email, String senha, Set<Perfil> perfils) {
    this.id = id;
    this.email = email;
    this.senha = senha;
    this.authorities = perfils.stream().map(x ->
    new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toSet());
  }

  public Long getId() {
    return id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return senha;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
