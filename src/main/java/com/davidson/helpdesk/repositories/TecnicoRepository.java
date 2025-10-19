package com.davidson.helpdesk.repositories;

import com.davidson.helpdesk.domain.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

}
