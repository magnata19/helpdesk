package com.davidson.helpdesk.repositories;

import com.davidson.helpdesk.domain.entity.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
}
