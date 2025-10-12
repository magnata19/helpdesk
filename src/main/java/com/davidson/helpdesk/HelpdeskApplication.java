package com.davidson.helpdesk;

import com.davidson.helpdesk.domain.entity.Chamado;
import com.davidson.helpdesk.domain.entity.Cliente;
import com.davidson.helpdesk.domain.entity.Tecnico;
import com.davidson.helpdesk.domain.enums.Prioridade;
import com.davidson.helpdesk.domain.enums.Status;
import com.davidson.helpdesk.repositories.ChamadoRepository;
import com.davidson.helpdesk.repositories.ClienteRepository;
import com.davidson.helpdesk.repositories.TecnicoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@SpringBootApplication
@RestController
public class HelpdeskApplication{

  public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

}
