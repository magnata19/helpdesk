package com.davidson.helpdesk.services;

import com.davidson.helpdesk.domain.dtos.ChamadoDto;
import com.davidson.helpdesk.domain.entity.Chamado;
import com.davidson.helpdesk.domain.entity.Cliente;
import com.davidson.helpdesk.domain.entity.Tecnico;
import com.davidson.helpdesk.domain.enums.Prioridade;
import com.davidson.helpdesk.domain.enums.Status;
import com.davidson.helpdesk.repositories.ChamadoRepository;
import com.davidson.helpdesk.services.exception.ObjnotfoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

  private final ChamadoRepository chamadoRepository;
  private final TecnicoService tecnicoService;
  private final ClienteService clienteService;

  public ChamadoService(ChamadoRepository chamadoRepository, TecnicoService tecnicoService, ClienteService clienteService) {
    this.chamadoRepository = chamadoRepository;
    this.tecnicoService = tecnicoService;
    this.clienteService = clienteService;
  }

  public Chamado findById(Long id) {
    Optional<Chamado> chamado = chamadoRepository.findById(id);
    return chamado.orElseThrow(() -> new ObjnotfoundException("Chamado não encontrado! Id: " + id));
  }

  public List<Chamado> findAll() {
    return chamadoRepository.findAll();
  }

  public Chamado create(@Valid ChamadoDto chamadoDto) {
    return chamadoRepository.save(newChamado(chamadoDto));
  }

  private Chamado newChamado(ChamadoDto chamadoDto) {
    Tecnico tecnico = tecnicoService.findById(chamadoDto.getTecnicoId());
    Cliente cliente = clienteService.findById(chamadoDto.getClienteId());

    Chamado chamado = new Chamado();
    if (chamadoDto.getId() != null) {
      chamado.setId(chamadoDto.getId()); // se for null é criação, se não é atualização
    }

    chamado.setTecnico(tecnico);
    chamado.setCliente(cliente);
    chamado.setPrioridade(Prioridade.toEnum(chamadoDto.getPrioridadeCod()));
    chamado.setStatus(Status.toEnum(chamadoDto.getStatusCod()));
    chamado.setTitulo(chamadoDto.getTitulo());
    chamado.setObservacoes(chamadoDto.getObservacoes());
    return chamado;
  }
}
