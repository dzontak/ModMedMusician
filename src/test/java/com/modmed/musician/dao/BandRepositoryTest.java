package com.modmed.musician.dao;

import com.modmed.musician.types.MusicGenre;
import com.modmed.musician.model.Band;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(Transactional.TxType.SUPPORTS)
@Slf4j
public class BandRepositoryTest {

  @Autowired BandRepository repository;

  @Test
  public void shouldFindAllBands() {
    Iterable<Band> allBands = repository.findAll();
    for (Band band : allBands) {
      assertNotNull(band);
      assertNotNull(band.getId());
      assertNotNull(band.getName());
      assertNotNull(band.getContact());
      assertNotNull(band.getGenre());
    }
  }

  @Test
  public void shouldFindBandByGenre() {
    Set<Band> jazzBands = repository.findByGenre(MusicGenre.JAZZ);
    for (Band band : jazzBands) {
      Assert.assertEquals(MusicGenre.JAZZ, band.getGenre());
    }
  }

  @Test
  public void shouldFindByContactName() {
    Set<Band> bandsByContact = repository.findByContactName("Jeff Dawn");
    for (Band band : bandsByContact) {
      assertEquals("Jeff Dawn", band.getContact().getName());
    }
  }

  @Test
  public void shouldFindByHomeCountry() {
    Set<Band> bandsByContact = repository.findByHomeCountry("London");
    for (Band band : bandsByContact) {
      assertEquals("London", band.getHome().getCountry());
    }
  }

  @Test
  public void shouldFindByHomeTown() {
    Set<Band> bandsByContact = repository.findByHomeTown("Chicago");
    for (Band band : bandsByContact) {
      assertEquals("Chicago", band.getHome().getTown());
    }
  }
  @Test
  public void shouldFindByName() {
    Band band = repository.findByName("ROP");
    assertNotNull(band);
    assertEquals("ROP", band.getName());

  }
}
