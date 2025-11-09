package com.davidson.helpdesk.repositories;

import com.davidson.helpdesk.domain.entity.Pessoa;
import com.davidson.helpdesk.domain.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
  Optional<Pessoa> findByCpf(String cpf);
  Optional<Pessoa> findByEmail(String email);
}
