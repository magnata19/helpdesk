package com.davidson.helpdesk.resource;

import com.davidson.helpdesk.domain.dtos.ClienteDto;
import com.davidson.helpdesk.domain.entity.Cliente;
import com.davidson.helpdesk.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

  private final ClienteService clienteService;

  public ClienteResource(ClienteService clienteService) {
    this.clienteService = clienteService;
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ClienteDto> findById(@PathVariable Long id) {
    Cliente obj = clienteService.findById(id);
    return ResponseEntity.ok().body(new ClienteDto(obj));
  }

  @GetMapping
  public ResponseEntity<List<ClienteDto>> getAll() {
    List<Cliente> list = clienteService.getAll();
    List<ClienteDto> listDto = list.stream().map(tec -> new ClienteDto(tec)).toList();
    return ResponseEntity.ok().body(listDto);
  }

  @PostMapping
  public ResponseEntity<ClienteDto> create(@Valid @RequestBody ClienteDto dto) {
    Cliente cliente = clienteService.create(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(cliente.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<ClienteDto> update(@PathVariable Long id, @Valid @RequestBody ClienteDto dto) {
    Cliente cliente = clienteService.update(id, dto);
    return ResponseEntity.ok().body(new ClienteDto(cliente));
  }


  @DeleteMapping(value = "/{id}")
  public ResponseEntity<ClienteDto> delete (@PathVariable Long id) {
    clienteService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
