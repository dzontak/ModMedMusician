package com.modmed.musician.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "musician")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "musician_id"))})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "musician_id_seq", initialValue = 100)
public class Musician extends BaseEntity {

  @Column(name = "musician_name")
  private String name;

  @Basic
  @Column(name = " born_on")
  @EqualsAndHashCode.Exclude
  private LocalDate bornOn;

  @Basic
  @Column(name = "died_on")
  @EqualsAndHashCode.Exclude
  private LocalDate diedOn;

  @ManyToOne
  @JoinColumn(name = "born_in")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Place bornIn;

  @ManyToOne
  @JoinColumn(name = "living_in")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Place livingIn;

  @OneToMany(mappedBy = "musician", fetch = FetchType.LAZY)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Composer> composers;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "musician")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Performer> performers;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "organizer")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Concert> organized;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Band> bands;
}
