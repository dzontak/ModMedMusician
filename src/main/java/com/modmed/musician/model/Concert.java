package com.modmed.musician.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "concert")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "concert_id"))})
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "concert_id_seq", initialValue = 100)
public class Concert extends BaseEntity {

  @Column(name = "concert_venue")
  private String venue;

  @Column(name = "concert_date")
  private LocalDate concertDate;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "concert_in")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Place heldIn;
}
