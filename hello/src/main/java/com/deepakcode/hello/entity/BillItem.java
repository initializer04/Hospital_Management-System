package com.deepakcode.hello.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "BillItem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillItem {
@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String description;

  @NotNull
  @Column(nullable = false)
  private Double amount;

  @ManyToOne(optional = false)
  private Bill bill;

  // getters and setters
}
