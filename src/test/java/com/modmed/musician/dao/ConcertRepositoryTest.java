package com.modmed.musician.dao;

import com.modmed.musician.model.Concert;
import com.modmed.musician.model.Musician;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(Transactional.TxType.SUPPORTS)
@Slf4j
public class ConcertRepositoryTest {

  @Autowired ConcertRepository repository;

  @Test
  public void shouldFindByVenue() {
    Iterable<Concert> concerts = repository.findByVenue("Bridgewater Hall");
    for (Concert c : concerts) {
      assertEquals("Bridgewater Hall", c.getVenue());
    }
  }

  @Test
  public void shouldFindByDateAfter() {
    LocalDate after = LocalDate.of(1975, 1, 1);
    Iterable<Concert> concerts = repository.findByDateAfter(after);
    assertNotNull(concerts);
    for (Concert c : concerts) {
      assertTrue(c.getDate().isAfter(after));
    }
  }

  @Test
  public void shouldFindByDateBefore() {
    LocalDate before = LocalDate.of(1985, 1, 1);
    Iterable<Concert> concerts = repository.findByDateBefore(before);
    assertNotNull(concerts);
    for (Concert c : concerts) {
      assertTrue(c.getDate().isBefore(before));
    }
  }

  @Test
  public void shouldFindByDateBetween() {
    LocalDate before = LocalDate.of(1975, 1, 1);
    LocalDate after = LocalDate.of(1985, 1, 1);
    Iterable<Concert> concerts = repository.findByDateBetween(before, after);
    assertNotNull(concerts);
    for (Concert c : concerts) {
      assertTrue(c.getDate().isBefore(before));
      assertTrue(c.getDate().isAfter(after));
    }
  }

  @Test
  public void shouldFindConcertsHeldInTown() {
    Iterable<Concert> concerts = repository.findByHeldInTown("New York");
    assertNotNull(concerts);
    for (Concert c : concerts) {
      assertTrue(c.getHeldIn().getTown().equals("New York"));
    }
  }

  @Test
  public void shouldFindConcertsHeldInCountry() {
    Iterable<Concert> concerts = repository.findByHeldInCountry("USA");
    assertNotNull(concerts);
    for (Concert c : concerts) {
      assertTrue(c.getHeldIn().getCountry().equals("USA"));
    }
  }

  @Test
  public void findByOrganizerName() {
    Iterable<Concert> concerts = repository.findByOrganizerName("James Steeple");
    assertNotNull(concerts);
    for (Concert c : concerts) {
      assertTrue(c.getOrganizer().getName().equals("James Steeple"));
    }
  }
}
