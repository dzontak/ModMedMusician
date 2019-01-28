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
@Table(name = "performer")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "performer_id"))})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "performer_id_seq", initialValue = 100)
public class Performer extends BaseEntity {

  @Column(name = "performer_type")
  @Convert(converter = GenreTypeConverter.class)
  private MusicGenre genre;

  @Column(name = "instrument")
  private String instrument;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "performer_is")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Musician musician;

  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(
      name = "plays_in",
      joinColumns = {@JoinColumn(name = "player_id")},
      inverseJoinColumns = {@JoinColumn(name = "band_id")})
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Band> bands;
}
