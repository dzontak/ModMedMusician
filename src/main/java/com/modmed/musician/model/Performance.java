package com.modmed.musician.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "performance")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "performance_id"))})
public class Performance extends BaseEntity {

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "gave")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Band band;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "performed")
  @ToString.Exclude
  private Composition composition;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "performed_in")
  @ToString.Exclude
  private Concert concert;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "conducted_by")
  @ToString.Exclude
  private Musician conductedBy;
}
