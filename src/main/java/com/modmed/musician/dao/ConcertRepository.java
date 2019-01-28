package com.modmed.musician.dao;

import com.modmed.musician.model.Concert;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;

/** The interface Concert repository. */
@RepositoryRestResource(collectionResourceRel = "concert", path = "concert")
public interface ConcertRepository extends PagingAndSortingRepository<Concert, Long> {

  /**
   * Find a concerts by venue name
   *
   * @param venue the venue
   * @return All concerts matching the venue
   */
  Iterable<Concert> findByVenue(String venue);

  /**
   * Find concerts held after a given date
   *
   * @param after the after
   * @return the concerts held after date
   */
  Iterable<Concert> findByDateAfter(LocalDate after);

  /**
   * Find concerts held in between 2 dates
   *
   * @param before the before date
   * @param after the after date
   * @return All concerts held between the 2 dates
   */
  Iterable<Concert> findByDateBetween(LocalDate before, LocalDate after);

  /**
   * Find concerts held before a given date
   *
   * @param before the before date
   * @return the concerts held before date
   */
  Iterable<Concert> findByDateBefore(LocalDate before);

  /**
   * Find concerts held in a given town
   *
   * @param town the town
   * @return Concerts held in this town
   */
  Iterable<Concert> findByHeldInTown(String town);

  /**
   * Find by held in country set.
   *
   * @param country the country
   * @return the set
   */
  Iterable<Concert> findByHeldInCountry(String country);

  /**
   * Find concerts by organizer's name
   *
   * @param name the name of concert organizer
   * @return  Concerts organized by <code>name</code>
   */
  Iterable<Concert> findByOrganizerName(String name);
}
