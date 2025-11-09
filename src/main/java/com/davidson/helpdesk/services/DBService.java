package com.davidson.helpdesk.services;

import com.davidson.helpdesk.domain.entity.Chamado;
import com.davidson.helpdesk.domain.entity.Cliente;
import com.davidson.helpdesk.domain.entity.Tecnico;
import com.davidson.helpdesk.domain.enums.Perfil;
import com.davidson.helpdesk.domain.enums.Prioridade;
import com.davidson.helpdesk.domain.enums.Status;
import com.davidson.helpdesk.repositories.ChamadoRepository;
import com.davidson.helpdesk.repositories.ClienteRepository;
import com.davidson.helpdesk.repositories.TecnicoRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

  private final ClienteRepository clienteRepository;
  private final TecnicoRepository tecnicoRepository;
  private final ChamadoRepository chamadoRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public DBService(ClienteRepository clienteRepository, TecnicoRepository tecnicoRepository, ChamadoRepository chamadoRepository, BCryptPasswordEncoder passwordEncoder) {
    this.clienteRepository = clienteRepository;
    this.tecnicoRepository = tecnicoRepository;
    this.chamadoRepository = chamadoRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void instanciaDB(){
    Cliente cli1 = new Cliente(null, "Davidson", "475.881.370-10", "deh@mail.com", passwordEncoder.encode("123456"));

    Tecnico tec1 = new Tecnico(null, "Pacifico", "593.308.440-90", "walter@mail.com",passwordEncoder.encode("654321"));
    tec1.addPerfil(Perfil.TECNICO);
    tec1.addPerfil(Perfil.ADMIN);

    Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "Concertar impressora", "A impressora não está funcionando", tec1, cli1);
    clienteRepository.saveAll(Arrays.asList(cli1));
    tecnicoRepository.saveAll(Arrays.asList(tec1));
    chamadoRepository.saveAll(Arrays.asList(ch1));
  }
}
