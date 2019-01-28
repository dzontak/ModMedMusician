package com.modmed.musician.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@MappedSuperclass
public abstract class BaseEntity implements Comparable<BaseEntity>, Serializable {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE")
  private Long id;

  @Column(name = "created_at")
  @EqualsAndHashCode.Exclude
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column(name = "updated_at")
  @EqualsAndHashCode.Exclude
  private LocalDateTime updatedAt = LocalDateTime.now();

  @PrePersist
  public void prePersist() {
    createdAt = updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  public void preUpdate() {
    updatedAt = LocalDateTime.now();
  }

  @Override
  public int compareTo(BaseEntity o) {
    return this.getId().compareTo(o.getId());
  }
}
