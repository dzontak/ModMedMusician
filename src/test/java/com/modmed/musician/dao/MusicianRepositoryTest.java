package com.modmed.musician.dao;

import com.github.javafaker.Faker;
import com.modmed.musician.model.Place;
import com.modmed.musician.types.MusicGenre;
import com.modmed.musician.model.Musician;
import lombok.extern.slf4j.Slf4j;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(Transactional.TxType.SUPPORTS)
@Slf4j
public class MusicianRepositoryTest {
  @Autowired MusicianRepository repository;
  @Autowired PlaceRepository placeRepository;

  @Test
  public void shouldFindMusicianByName() {
    Musician musician = repository.findByName("Tony Smythe");
    assertNotNull(musician);
    assertEquals("Tony Smythe", musician.getName());
    assertNotNull(musician.getComposers());
    assertNotNull(musician.getId());
    assertNotNull(musician.getBornIn());
    assertNotNull(musician.getBornOn());
    assertNotNull(musician.getLivingIn());
  }

  @Test
  public void shouldFindByComposerGenre() {
    Set<Musician> byComposerGenre = repository.findByComposersGenre(MusicGenre.CLASSICAL);
    for (Musician m : byComposerGenre) {
      assertNotNull(
          m.getComposers().stream()
              .filter(p -> p.getGenre().equals(MusicGenre.CLASSICAL))
              .findFirst()
              .orElse(null));
    }
  }

  @Test
  public void shouldFindMusiciansBornInCountry() {
    Set<Musician> musicians = repository.findByBornInCountry("London");
    assertNotNull(musicians);
    for (Musician m : musicians) {
      assertEquals("London", m.getBornIn().getCountry());
    }
  }

  @Test
  public void shouldFindMusiciansBornInTown() {
    Set<Musician> musicians = repository.findByBornInTown("Glasgow");
    assertNotNull(musicians);
    for (Musician m : musicians) {
      assertEquals("Glasgow", m.getBornIn().getTown());
    }
  }

  @Test
  public void shouldFindMusiciansLivingInCountry() {
    Set<Musician> musicians = repository.findByLivingInCountry("London");
    assertNotNull(musicians);
    for (Musician m : musicians) {
      assertEquals("London", m.getLivingIn().getCountry());
    }
  }

  @Test
  public void shouldFindMusiciansLivingInTown() {
    Set<Musician> musicians = repository.findByLivingInTown("Glasgow");
    assertNotNull(musicians);
    for (Musician m : musicians) {
      assertEquals("Glasgow", m.getLivingIn().getTown());
    }
  }

  @Test
  public void shouldFindMusiciansBornAfterAGivenDate() throws ParseException {
    LocalDate birthdate = LocalDate.of(1975, 1, 1);
    Set<Musician> musicians = repository.findByBornOnAfter(birthdate);
    assertNotNull(musicians);
    for (Musician m : musicians) {
      assertTrue(m.getBornOn().isAfter(birthdate));
    }
  }

  @Test
  public void shouldFindMusiciansBornBeforeAGivenDate() {
    LocalDate birthdate = LocalDate.of(1975, 1, 1);
    Set<Musician> musicians = repository.findByBornOnBefore(birthdate);
    assertNotNull(musicians);
    for (Musician m : musicians) {
      assertTrue(m.getBornOn().isBefore(birthdate));
    }
  }

  @Test
  public void shouldFindByBornOnBetween() {
    LocalDate startDate = LocalDate.of(1945, 4, 1);
    LocalDate endDate = LocalDate.of(1978, 4, 1);
    Set<Musician> musiciansBornInBetween = repository.findByBornOnBetween(startDate, endDate);
    for (Musician m : musiciansBornInBetween) {
      assertTrue(m.getBornOn().isAfter(startDate));
      assertTrue(m.getBornOn().isBefore(endDate));
    }
  }

  @Test
  public void shouldFindByPerformerInstrument() {
    Set<Musician> musicians = repository.findByPerformersInstrument("viola");
    for (Musician m : musicians) {

      assertNotNull(
          m.getPerformers().stream()
              .filter(p -> p.getInstrument().equals("viola"))
              .findFirst()
              .orElse(null));
    }
  }

  @Test
  public void shouldCreateMusician() {
    Faker faker = new Faker();
    Musician musician =
        Musician.builder()
            .name(faker.name().name())
            .bornOn(
                Instant.ofEpochMilli(faker.date().birthday().getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate())
            .build();

    Place livingIn =
        Place.builder().town(faker.address().cityName()).country(faker.address().country()).build();
    placeRepository.save(livingIn);
    musician.setLivingIn(livingIn);
    Place bornIn =
        Place.builder().town(faker.address().cityName()).country(faker.address().country()).build();
    placeRepository.save(bornIn);
    musician.setBornIn(bornIn);

    Musician save = repository.save(musician);
    assertEquals(musician.getName(), save.getName());
    assertEquals(musician.getBornOn(), save.getBornOn());
    assertEquals(musician.getBornOn(), save.getBornOn());
    assertEquals(musician.getBornIn().getTown(), save.getBornIn().getTown());
    assertEquals(musician.getLivingIn().getTown(), save.getLivingIn().getTown());
  }
}
