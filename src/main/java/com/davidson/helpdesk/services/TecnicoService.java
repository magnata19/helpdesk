package com.davidson.helpdesk.services;

import com.davidson.helpdesk.domain.entity.Tecnico;
import com.davidson.helpdesk.repositories.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

  private final TecnicoRepository tecnicoRepository;

  public TecnicoService(TecnicoRepository tecnicoRepository) {
    this.tecnicoRepository = tecnicoRepository;
  }

  public Tecnico findById(Long id){
    Optional<Tecnico> obj = tecnicoRepository.findById(id);
    return obj.orElse(null);
  }

  public List<Tecnico> getAll(){
    return tecnicoRepository.findAll();
  }
}
