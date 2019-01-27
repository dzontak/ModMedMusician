package com.modmed.musician;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class ModMedMusicianApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(ModMedMusicianApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
  }
}
