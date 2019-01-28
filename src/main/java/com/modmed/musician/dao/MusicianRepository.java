package com.modmed.musician.dao;

import com.modmed.musician.model.Musician;
import com.modmed.musician.types.MusicGenre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.Set;

/** The interface Musician repository. */
@RepositoryRestResource(collectionResourceRel = "musician", path = "musician")
public interface MusicianRepository extends CrudRepository<Musician, Long> {

  /**
   * Find by name musician.
   *
   * @param name the name
   * @return the musician
   */
  Musician findByName(String name);

  /**
   * Find by born in country set.
   *
   * @param country the country
   * @return the set
   */
  Set<Musician> findByBornInCountry(String country);

  /**
   * Find by born in town set.
   *
   * @param town the town
   * @return the set
   */
  Set<Musician> findByBornInTown(String town);

  /**
   * Find by composer genre set.
   *
   * @param genre the genre
   * @return the set
   */
  Set<Musician> findByComposerGenre(MusicGenre genre);

  /**
   * Find by born on after set.
   *
   * @param after the after
   * @return the set
   */
  Set<Musician> findByBornOnAfter(LocalDate after);

  /**
   * Find by born on before set.
   *
   * @param before the after
   * @return the set
   */
  Set<Musician> findByBornOnBefore(LocalDate before);

  /**
   * Find musicians born between startDate and endDate
   * @param startDate A startDate to search for
   * @param endDate An endDate to search for
   * @return Musicians born between startDate and endDate
   */
  Set<Musician> findByBornOnBetween(LocalDate startDate, LocalDate endDate);

  /**
   * Find by living in country set.
   *
   * @param country the country
   * @return the set
   */
  Set<Musician> findByLivingInCountry(String country);

  /**
   * Find by living in town set.
   *
   * @param country the country
   * @return the set
   */
  Set<Musician> findByLivingInTown(String country);

  /**
   * Find Musicians by the instrument they play
   *
   * @param instrument (violin viola banjo guitar trumpet bass flute cornet
   *                   drums cello trombone horn clarinet)
   * @return All musicians that play a given instrument
   */
  //TODO: make instrument an enumeration

  Set<Musician> findByPerformersInstrument(String instrument);
}
