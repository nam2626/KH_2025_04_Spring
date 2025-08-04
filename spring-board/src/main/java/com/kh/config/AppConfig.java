package com.kh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new CustomPasswordEncoder();
  }
}
