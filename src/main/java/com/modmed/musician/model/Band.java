package com.modmed.musician.model;

import com.modmed.musician.types.GenreTypeConverter;
import com.modmed.musician.types.MusicGenre;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "band")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "band_id"))})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "band_id_seq", initialValue = 100)
public class Band extends BaseEntity {

  @Column(name = "band_name")
  private String name;

  @OneToOne
  @JoinColumn(name = "band_home")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Place home;

  @Column(name = "band_type")
  @Convert(converter = GenreTypeConverter.class)
  private MusicGenre genre;

  @Column(name = "band_start_date")
  private LocalDate startDate;

  @OneToOne
  @JoinColumn(name = "band_contact")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Musician contact;

  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(
      name = "plays_in",
      joinColumns = {@JoinColumn(name = "band_id")},
      inverseJoinColumns = {@JoinColumn(name = "player_id")})
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Performer> performers;
}
