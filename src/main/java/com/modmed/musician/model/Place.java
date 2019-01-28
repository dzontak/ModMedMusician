package com.modmed.musician.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "place")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "place_id"))})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "place_id_seq", initialValue = 100)
public class Place extends BaseEntity {

  @Column(name = "place_town")
  private String town;

  @Column(name = "place_country")
  private String country;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "livingIn")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Musician> musiciansLivingIn;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "bornIn")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Musician> musiciansBornIn;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "heldIn")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Concert> concerts;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "home")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Band> bands;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "composedIn")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Composition> compositions;
}


