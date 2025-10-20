package com.davidson.helpdesk.resource;

import com.davidson.helpdesk.domain.dtos.TecnicoDto;
import com.davidson.helpdesk.domain.entity.Tecnico;
import com.davidson.helpdesk.services.TecnicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
  public ResponseEntity<List<TecnicoDto>> getAll() {
    List<Tecnico> list = tecnicoService.getAll();
    List<TecnicoDto> listDto = list.stream().map(tec -> new TecnicoDto(tec)).toList();
    return ResponseEntity.ok().body(listDto);
  }

  @PostMapping
  public ResponseEntity<TecnicoDto> create(@Valid @RequestBody TecnicoDto dto) {
    Tecnico tecnico = tecnicoService.create(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(tecnico.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

}
