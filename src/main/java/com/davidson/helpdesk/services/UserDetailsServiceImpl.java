package com.davidson.helpdesk.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.davidson.helpdesk.domain.entity.Pessoa;
import com.davidson.helpdesk.repositories.PessoaRepository;
import com.davidson.helpdesk.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final PessoaRepository pessoaRepository;

  public UserDetailsServiceImpl(PessoaRepository pessoaRepository) {
    this.pessoaRepository = pessoaRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<Pessoa> user = pessoaRepository.findByEmail(email);
    if (user.isPresent()) {
      return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getSenha(), user.get().getPerfis());
    }
    throw new UsernameNotFoundException("User not found by email: " + email);
  }

}
