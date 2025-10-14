package com.davidson.helpdesk.resource;

import com.davidson.helpdesk.domain.dtos.TecnicoDto;
import com.davidson.helpdesk.domain.entity.Tecnico;
import com.davidson.helpdesk.services.TecnicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoResource {

  private final TecnicoService tecnicoService;

  public TecnicoResource(TecnicoService tecnicoService) {
    this.tecnicoService = tecnicoService;
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<TecnicoDto> findById(@PathVariable Long id) {
    Tecnico obj = tecnicoService.findById(id);
    return ResponseEntity.ok().body(new TecnicoDto(obj));
  }

  @GetMapping
  public ResponseEntity<List<Tecnico>> getAll() {
    List<Tecnico> list = tecnicoService.getAll();
    return ResponseEntity.ok().body(list);
  }

}
