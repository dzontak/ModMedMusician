package com.modmed.musician.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "composition")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "composition_id"))})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "composition_id_seq", initialValue = 100)
public class Composition extends BaseEntity {

  @Column(name = "composition_title")
  private String title;

  @Column(name = "composition_date")
  private LocalDate composedOn;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "composed_in")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Place composedIn;

  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(
      name = "has_composed",
      joinColumns = {@JoinColumn(name = "composition_id")},
      inverseJoinColumns = {@JoinColumn(name = "composer_id")})
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Composer> composers;
}
