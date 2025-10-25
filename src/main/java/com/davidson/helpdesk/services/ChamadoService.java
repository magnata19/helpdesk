package com.davidson.helpdesk.services;

import com.davidson.helpdesk.domain.entity.Chamado;
import com.davidson.helpdesk.repositories.ChamadoRepository;
import com.davidson.helpdesk.services.exception.ObjnotfoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

  private final ChamadoRepository chamadoRepository;

  public  ChamadoService(ChamadoRepository chamadoRepository) {
    this.chamadoRepository = chamadoRepository;
  }

  public Chamado findById(Long id) {
    Optional<Chamado> chamado = chamadoRepository.findById(id);
    return chamado.orElseThrow(() -> new ObjnotfoundException("Chamado não encontrado! Id: " + id));
  }

  public List<Chamado> findAll() {
    return  chamadoRepository.findAll();
  }
}
