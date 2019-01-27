package com.modmed.musician.dao;

import com.modmed.musician.model.Place;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

/** The interface Place repository. */
@RepositoryRestResource(collectionResourceRel = "place", path = "place")
public interface PlaceRepository extends CrudRepository<Place, Long> {

  /**
   * Find a place by town's name.
   *
   * @param town the town
   * @return the place by town name
   */
  Place findPlaceByTown(String town);

  /**
   * Find all places by country.
   *
   * @param country the country
   * @return All places for a given country
   */
  Set<Place> findPlacesByCountry(String country);
}
