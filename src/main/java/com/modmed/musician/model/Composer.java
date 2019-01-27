package com.modmed.musician.model;

import com.modmed.musician.types.GenreTypeConverter;
import com.modmed.musician.types.MusicGenre;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "composer")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "composer_id"))})
public class Composer extends BaseEntity {

  @Column(name = "composer_type")
  @Convert(converter = GenreTypeConverter.class)
  private MusicGenre genre;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "composer_is")
  @ToString.Exclude
  private Musician musician;

  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(
      name = "has_composed",
      joinColumns = {@JoinColumn(name = "composer_id")},
      inverseJoinColumns = {@JoinColumn(name = "composition_id")})
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Composition> compositions;
}
