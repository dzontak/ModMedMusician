package com.modmed.musician.dao;

import com.modmed.musician.model.Performance;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(Transactional.TxType.SUPPORTS)
@Slf4j
public class PerformanceRepositoryTest {

  @Autowired PerformanceRepository repository;

  @Test
  public void shouldFindByBandName() {
    Set<Performance> performances = repository.findByBandName("AASO");
    for (Performance p : performances) {
      assertEquals("AASO", p.getBand().getName());
    }
  }

  @Test
  public void shouldFindByConcertVenue() {
    Set<Performance> performances = repository.findByConcertVenue("Usher Hall");
    for (Performance p : performances) {
      assertEquals("Usher Hall", p.getConcert().getVenue());
    }
  }

  @Test
  public void shouldFindByConductedByName() {
    Set<Performance> performances = repository.findByConductedByName("Harriet Smithson");
    for (Performance p : performances) {
      assertEquals("Harriet Smithson", p.getConductedBy().getName());
    }
  }

  @Test
  public void shouldFindByCompositionTitle() {
    Set<Performance> performances = repository.findByCompositionTitle("Simple Love Song");
    for (Performance p : performances) {
      assertEquals("Simple Love Song", p.getComposition().getTitle());
    }
  }
}
