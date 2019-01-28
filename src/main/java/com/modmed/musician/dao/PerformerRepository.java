package com.modmed.musician.dao;

import com.modmed.musician.model.Performer;
import com.modmed.musician.types.MusicGenre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

/** The interface Performer repository. */
@RepositoryRestResource(collectionResourceRel = "performer", path = "performer")
public interface PerformerRepository extends CrudRepository<Performer, Long> {
  /**
   * Find performers by <code>{@link MusicGenre}</code>
   *
   * @param genre A <code>MusicGenre</code>
   * @return a <code>Set</code> of performers
   */
  Set<Performer> findByGenre(MusicGenre genre);

  /**
   * Find performers by Musician's name
   *
   * @param name Musician's name
   * @return a <code>Set</code> of performers
   */
  Set<Performer> findByMusicianName(String name);

  /**
   * Find perfomer by a band's name
   *
   * @param band A name of the band
   * @return a <code>Set</code> of performers
   */
  Set<Performer> findByBandsName(String band);
}
