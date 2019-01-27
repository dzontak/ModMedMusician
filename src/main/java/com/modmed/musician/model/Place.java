package com.modmed.musician.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "place")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "place_id"))})
public class Place extends BaseEntity {

  @Column(name = "place_town")
  private String town;

  @Column(name = "place_country")
  private String country;
}
