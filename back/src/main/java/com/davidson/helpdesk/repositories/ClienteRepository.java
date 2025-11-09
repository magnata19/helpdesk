package com.davidson.helpdesk.repositories;

import com.davidson.helpdesk.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
