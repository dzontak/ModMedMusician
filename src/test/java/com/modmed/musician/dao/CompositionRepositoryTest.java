package com.modmed.musician.dao;

import com.modmed.musician.types.MusicGenre;
import com.modmed.musician.model.Composition;
import com.modmed.musician.model.Composer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(Transactional.TxType.SUPPORTS)
@Slf4j
public class CompositionRepositoryTest {

  @Autowired CompositionRepository repository;

  @Test
  public void shouldFindCompositionByTitle() {
    Composition composition = repository.findByTitle("Opus 1");
    assertNotNull(composition);
    assertEquals("Opus 1", composition.getTitle());
  }

  @Test
  public void shouldFindComposersByComposition() {
    Composition composition = repository.findByTitle("Opus 1");
    assertNotNull(composition);
    assertEquals("Opus 1", composition.getTitle());
    Set<Composer> composers = composition.getComposers();
    assertNotNull(composers);
  }

  @Test
  public void shouldFindCompositionsComposedInCountry() {
    Set<Composition> compositions = repository.findByComposedInCountry("London");
    assertNotNull(compositions);
    for (Composition c : compositions) {
      assertEquals("London", c.getComposedIn().getCountry());
    }
  }

  @Test
  public void shouldFindCompositionsComposedInTown() {
    Set<Composition> compositions = repository.findByComposedInTown("Glasgow");
    assertNotNull(compositions);
    for (Composition c : compositions) {
      assertEquals("Glasgow", c.getComposedIn().getTown());
    }
  }

  @Test
  public void shouldFindCompositionsByGenre() {
    Set<Composition> compositions = repository.findByComposersGenre(MusicGenre.CLASSICAL);
    assertNotNull(compositions);
    for (Composition c : compositions) {
      for (Composer composer : c.getComposers()) {
        assertEquals(MusicGenre.CLASSICAL, composer.getGenre());
      }
    }
  }

  @Test
  public void sholdFindByComposedOnBefore() {
    LocalDate beforeDate = LocalDate.of(1988, 1, 17);
    Set<Composition> composedBefore = repository.findByComposedOnBefore(beforeDate);
    for (Composition comp : composedBefore) {
      assertTrue(comp.getComposedOn().isBefore(beforeDate));
    }
  }

  @Test
  public void sholdFindByComposedOnAfter() {
    LocalDate afterDate = LocalDate.of(1980, 4, 1);
    Set<Composition> composedBefore = repository.findByComposedOnAfter(afterDate);
    for (Composition comp : composedBefore) {
      assertTrue(comp.getComposedOn().isAfter(afterDate));
    }
  }

  @Test
  public void sholdFindByComposedOnBetween() {
    LocalDate startDate = LocalDate.of(1980, 4, 1);
    LocalDate endDate = LocalDate.of(2000, 4, 1);
    Set<Composition> composedBefore = repository.findByComposedOnBetween(startDate, endDate);
    for (Composition comp : composedBefore) {
      assertTrue(comp.getComposedOn().isAfter(startDate));
      assertTrue(comp.getComposedOn().isBefore(endDate));
    }
  }
}
