package com.modmed.musician.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class BaseEntity implements Comparable<BaseEntity>, Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

//  @Column(nullable = false)
//  @EqualsAndHashCode.Exclude
//  private LocalDateTime createdAt;
//
//  @Column(nullable = false)
//  @EqualsAndHashCode.Exclude
//  private LocalDateTime updatedAt;
//
//  @PrePersist
//  public void prePersist() {
//    createdAt = updatedAt = LocalDateTime.now();
//  }
//
//  @PreUpdate
//  public void preUpdate() {
//    updatedAt = LocalDateTime.now();
//  }

  @Override
  public int compareTo(BaseEntity o) {
    return this.getId().compareTo(o.getId());
  }
}
