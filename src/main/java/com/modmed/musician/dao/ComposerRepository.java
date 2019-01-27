package com.modmed.musician.dao;

import com.modmed.musician.types.MusicGenre;
import com.modmed.musician.model.Composer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;
/** The interface Composer repository. */
@RepositoryRestResource(collectionResourceRel = "composer", path = "composer")
public interface ComposerRepository extends CrudRepository<Composer, Long> {

  /**
   * Finds all Composers by music genre
   *
   * @param genre <code>{@link MusicGenre}</code>
   * @return A list of composers for a given genre
   */
  Set<Composer> findComposersByGenre(MusicGenre genre);

  /**
   * Find composer by name
   *
   * @param name the name of the composer
   * @return The composer with the name
   */
  Composer findComposerByMusicianName(String name);

  /**
   * Finds composers who composed a composition with this title
   * @param title A title of a composition
   * @return A collection of composers who composed a composition of this title
   */
  Set<Composer> findComposersByCompositionsTitle(String title);


}
