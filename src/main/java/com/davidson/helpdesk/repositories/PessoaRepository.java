package com.davidson.helpdesk.repositories;

import com.davidson.helpdesk.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
