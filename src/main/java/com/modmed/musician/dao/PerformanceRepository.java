package com.modmed.musician.dao;

import com.modmed.musician.model.Performance;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

/** The interface Performance repository. */
@RepositoryRestResource(collectionResourceRel = "performance", path = "performance")
public interface PerformanceRepository extends PagingAndSortingRepository<Performance, Long> {
  /**
   * Find performance by band's name
   *
   * @param name A name of the band
   * @return All performances of a given band
   */
  Set<Performance> findByBandName(String name);

  /**
   * Find performances by composition title
   *
   * @param compositionTitle A title of the composition performed
   * @return All performances of a composition
   */
  Set<Performance> findByCompositionTitle(String compositionTitle);

  /**
   * Find all performances given in a particular concert venue by venue name
   *
   * @param vanue The name of the venue
   * @return All performances for a given venue
   */
  Set<Performance> findByConcertVenue(String vanue);

  /**
   * Find all peformances by musician's name
   *
   * @param musicianName The name of a musician
   * @return All peformances given by a musician by the given name
   */
  Set<Performance> findByConductedByName(String musicianName);
}
