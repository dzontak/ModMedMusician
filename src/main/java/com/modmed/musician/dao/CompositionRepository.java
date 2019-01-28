package com.modmed.musician.dao;

import com.modmed.musician.model.Composition;
import com.modmed.musician.types.MusicGenre;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.Set;
/** The interface Composition repository. */
@RepositoryRestResource(collectionResourceRel = "composition", path = "composition")
public interface CompositionRepository extends PagingAndSortingRepository<Composition, Long> {

  /**
   * Find by title composition.
   *
   * @param title the title
   * @return the composition
   */
  Composition findByTitle(String title);
  /**
   * Find by composed in town set.
   *
   * @param town the town
   * @return the set
   */
  Set<Composition> findByComposedInTown(String town);
  /**
   * Find by composed in country set.
   *
   * @param country the country
   * @return the set
   */
  Set<Composition> findByComposedInCountry(String country);
  /**
   * Find by composers genre set.
   *
   * @param genre the genre
   * @return the set
   */
  Set<Composition> findByComposersGenre(MusicGenre genre);

  /**
   * Find Compositions composed before a given date
   *
   * @param beforeDate A date
   * @return All compositions composed before a given date
   */
  Set<Composition> findByComposedOnBefore(LocalDate beforeDate);

  /**
   * Find Compositions composed after a given date
   *
   * @param afterDate A date
   * @return All compositions composed after a given date
   */
  Set<Composition> findByComposedOnAfter(LocalDate afterDate);

    /**
     * Find Compositions composed in between <code>startDate</code> and <code>endDate</code>
     * @param startDate A start date in range
     * @param endDate An end date in range
     * @return Compositions composed between startDate and endDate
     */
  Set<Composition> findByComposedOnBetween(LocalDate startDate, LocalDate endDate);
}
