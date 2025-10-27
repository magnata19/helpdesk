package com.davidson.helpdesk.services;

import com.davidson.helpdesk.domain.dtos.ClienteDto;
import com.davidson.helpdesk.domain.entity.Pessoa;
import com.davidson.helpdesk.domain.entity.Cliente;
import com.davidson.helpdesk.repositories.ClienteRepository;
import com.davidson.helpdesk.repositories.PessoaRepository;
import com.davidson.helpdesk.services.exception.DataIntegrityViolationException;
import com.davidson.helpdesk.services.exception.ObjnotfoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

  private final BCryptPasswordEncoder passwordEncoder;
  private final ClienteRepository clienteRepository;
  private final PessoaRepository pessoaRepository;

  public ClienteService(ClienteRepository clienteRepository, PessoaRepository pessoaRepository, BCryptPasswordEncoder passwordEncoder) {
    this.clienteRepository = clienteRepository;
    this.pessoaRepository = pessoaRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Cliente findById(Long id){
    Optional<Cliente> obj = clienteRepository.findById(id);
    return obj.orElseThrow(() -> new ObjnotfoundException("Objeto não encontrado! Id: " + id));
  }

  public List<Cliente> getAll(){
    return clienteRepository.findAll();
  }

  public Cliente create(ClienteDto dto) {
    dto.setId(null); // garantir que é uma criação e não uma atualização
    dto.setSenha(passwordEncoder.encode(dto.getSenha()));
    validaPorCpfEEmail(dto);
    Cliente newTec = new Cliente(dto);
    return clienteRepository.save(newTec);
  }


  public Cliente update(Long id, ClienteDto dto) {
    dto.setId(id);
    dto.setSenha(passwordEncoder.encode(dto.getSenha()));
    Cliente oldObj = findById(id);
    validaPorCpfEEmail(dto);
    oldObj = new Cliente(dto);
    return  clienteRepository.save(oldObj);
  }

  public void delete(Long id) {
    Cliente obj = findById(id);
    if (obj.getChamados().size() > 0) {
      throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
    } else {
      clienteRepository.deleteById(id);
    }
  }

  private void validaPorCpfEEmail(ClienteDto dto) {
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

