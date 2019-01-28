package com.modmed.musician.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
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
}
