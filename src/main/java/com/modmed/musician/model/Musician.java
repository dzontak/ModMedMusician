package com.modmed.musician.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "musician")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "musician_id"))})
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

  @OneToOne
  @JoinColumn(name = "born_in")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Place bornIn;

  @OneToOne
  @JoinColumn(name = "living_in")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Place livingIn;

  @OneToOne(mappedBy = "musician", fetch = FetchType.LAZY)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Composer composer;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "musician")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Performer> performers;
}
