package com.modmed.musician.dao;

import com.modmed.musician.model.Band;
import com.modmed.musician.types.MusicGenre;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

/** The interface Band repository. */
@RepositoryRestResource(collectionResourceRel = "band", path = "band")
public interface BandRepository extends PagingAndSortingRepository<Band, Long> {
  /**
   * Find by name band.
   *
   * @param name the name
   * @return the band
   */
  Band findByName(String name);

  /**
   * Find by type set.
   *
   * @param genre the genre
   * @return the set
   */
  Set<Band> findByGenre(MusicGenre genre);

  /**
   * Find by home town set.
   *
   * @param town the town
   * @return the set
   */
  Set<Band> findByHomeTown(String town);

  /**
   * Find by home country set.
   *
   * @param country the country
   * @return the set
   */
  Set<Band> findByHomeCountry(String country);

  /**
   * Find bands associated with a contact.
   *
   * @param name The contact's name
   * @return All bands associated with the contact of a given name.
   */
  Set<Band> findByContactName(String name);
}
