package com.modmed.musician.dao;

import com.modmed.musician.types.MusicGenre;
import com.modmed.musician.model.Band;
import com.modmed.musician.model.Performer;
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
public class PerformerRepositoryTest {
  @Autowired PerformerRepository repository;

  @Test
  public void shouldfindByMusicianName() {
    Set<Performer> performers = repository.findByMusicianName("Theo Mengel");
    for (Performer p : performers) {
      assertEquals("Theo Mengel", p.getMusician().getName());
    }
  }

  @Test
  public void shouldFinByBandsName() {
    Set<Performer> performers = repository.findByBandsName("The Rest");
    for (Performer p : performers) {
      for (Band band : p.getBands()) {
        assertEquals("The Rest", band.getName());
      }
    }
  }

  @Test
  public void shouldFindByGenre() {
    Set<Performer> performers = repository.findByGenre(MusicGenre.CLASSICAL);
    for (Performer p : performers) {
      assertEquals(MusicGenre.CLASSICAL, p.getGenre());
    }
  }
}
