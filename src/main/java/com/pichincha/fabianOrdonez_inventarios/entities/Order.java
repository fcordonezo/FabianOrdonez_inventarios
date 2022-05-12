package com.pichincha.fabianOrdonez_inventarios.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Date createdAt;

  private Float totalAmount;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderDetail> orderDetails;
}
