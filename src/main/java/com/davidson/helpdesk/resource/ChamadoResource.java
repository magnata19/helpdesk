package com.davidson.helpdesk.resource;

import com.davidson.helpdesk.domain.dtos.ChamadoDto;
import com.davidson.helpdesk.domain.entity.Chamado;
import com.davidson.helpdesk.services.ChamadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

  private final ChamadoService chamadoService;

  public ChamadoResource(ChamadoService chamadoService) {
    this.chamadoService = chamadoService;
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ChamadoDto> findById(@PathVariable(value = "id") Long id) {
    Chamado chamado = chamadoService.findById(id);
    return ResponseEntity.ok().body(new ChamadoDto(chamado));
  }

  @GetMapping
  public ResponseEntity<List<ChamadoDto>> getAll(){
    List<Chamado> list = chamadoService.findAll();
    List<ChamadoDto> listDto = list.stream().map(ch -> new ChamadoDto(ch)).toList();
    return ResponseEntity.ok().body(listDto);
  }
}
