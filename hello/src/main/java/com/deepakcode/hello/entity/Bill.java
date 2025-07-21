package com.deepakcode.hello.entity;

import java.time.LocalDateTime;
import java.util.List;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "bill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private Double totalAmount;

  @NotNull
  @Column(nullable = false)
  private String status; // UNPAID, PAID

  @NotNull
  @Column(nullable = false, unique = true)
  private String orderId;

  @NotNull
  @Column(nullable = false)
  private LocalDateTime dateGenerated;

  @ManyToOne(optional = false)
  private Patient patient;

  @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<BillItem> items;
}