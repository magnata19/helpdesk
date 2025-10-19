package com.davidson.helpdesk.services;

import com.davidson.helpdesk.domain.dtos.TecnicoDto;
import com.davidson.helpdesk.domain.entity.Pessoa;
import com.davidson.helpdesk.domain.entity.Tecnico;
import com.davidson.helpdesk.repositories.PessoaRepository;
import com.davidson.helpdesk.repositories.TecnicoRepository;
import com.davidson.helpdesk.services.exception.DataIntegrityViolationException;
import com.davidson.helpdesk.services.exception.ObjnotfoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TecnicoService {

  private final TecnicoRepository tecnicoRepository;
  private final PessoaRepository pessoaRepository;

  public TecnicoService(TecnicoRepository tecnicoRepository, PessoaRepository pessoaRepository) {
    this.tecnicoRepository = tecnicoRepository;
    this.pessoaRepository = pessoaRepository;
  }

  public Tecnico findById(Long id){
    Optional<Tecnico> obj = tecnicoRepository.findById(id);
    return obj.orElseThrow(() -> new ObjnotfoundException("Objeto não encontrado! Id: " + id));
  }

  public List<Tecnico> getAll(){
    return tecnicoRepository.findAll();
  }

  public Tecnico create(TecnicoDto dto) {
    dto.setId(null); // garantir que é uma criação e não uma atualização
    validaPorCpfEEmail(dto);
    Tecnico newTec = new Tecnico(dto);
    return tecnicoRepository.save(newTec);
  }

  private void validaPorCpfEEmail(TecnicoDto dto) {
    Optional<Pessoa> obj = pessoaRepository.findByCpf(dto.getCpf());
     if (obj.isPresent() && !Objects.equals(obj.get().getId(), dto.getId())) {
      throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
    }

     obj = pessoaRepository.findByEmail(dto.getEmail());
    if (obj.isPresent() && !Objects.equals(obj.get().getId(), dto.getId())) {
      throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
    }
  }
}

