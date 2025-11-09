package com.davidson.helpdesk.config;

import com.davidson.helpdesk.services.DBService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

  private final DBService dbService;

  public TestConfig(DBService dbService) {
    this.dbService = dbService;
  }

  @Bean
  public void instanciaDB() {
    this.dbService.instanciaDB();
  }
}
