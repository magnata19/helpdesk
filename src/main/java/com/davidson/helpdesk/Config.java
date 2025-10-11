package com.davidson.helpdesk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Bean
  public String funciona(){
    System.out.println("Funcionou");
    return "ok";
  }
}
