package com.davidson.helpdesk.services;

import com.davidson.helpdesk.domain.dtos.TecnicoDto;
import com.davidson.helpdesk.domain.entity.Tecnico;
import com.davidson.helpdesk.repositories.TecnicoRepository;
import com.davidson.helpdesk.services.exception.ObjnotfoundException;
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
    return obj.orElseThrow(() ->new ObjnotfoundException("Objeto não encontrado! Id: " + id));
  }

  public List<Tecnico> getAll(){
    return tecnicoRepository.findAll();
  }

  public Tecnico create(TecnicoDto dto) {
    dto.setId(null); // garantir que é uma criação e não uma atualização
    Tecnico newTec = new Tecnico(dto);
    return tecnicoRepository.save(newTec);
  }
}
