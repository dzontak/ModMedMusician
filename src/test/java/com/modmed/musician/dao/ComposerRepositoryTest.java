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
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(Transactional.TxType.SUPPORTS)
@Slf4j
public class ComposerRepositoryTest {

  @Autowired ComposerRepository repository;

  @Test
  public void shouldFindComposerByMusician() {
    Composer composer = repository.findComposerByMusicianName("Fred Bloggs");
    assertEquals("Fred Bloggs", composer.getMusician().getName());
  }

  @Test
  public void shouldFindComposersByGenre() {
    Set<Composer> composersByGenre = repository.findComposersByGenre(MusicGenre.CLASSICAL);
    for (Composer c : composersByGenre) {
      assertEquals("Classical", c.getGenre().getCode());
    }
  }

  @Test
  public void shouldFindAllCompositionsByComposer() {
    Composer composer = repository.findComposerByMusicianName("Fred Bloggs");
    assertEquals("Fred Bloggs", composer.getMusician().getName());
    Set<Composition> compositions = composer.getCompositions();
    for (Composition comp : compositions) {
      for (Composer c : comp.getComposers()) {
        assertEquals(composer.getId(), c.getId());
      }
    }
  }

  @Test
  public void shouldFindComposersByCompositionsTitle() {
    Set<Composer> composersByCompositionsTitle =
        repository.findComposersByCompositionsTitle("Blue Roses");
    for (Composer c : composersByCompositionsTitle) {
      Composition composition =
          c.getCompositions().stream()
              .filter(comp -> comp.getTitle().equals("Blue Roses"))
              .findFirst()
              .orElse(null);
      assertNotNull(composition);
    }
  }
}
