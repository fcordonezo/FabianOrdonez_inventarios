package com.pichincha.fabianOrdonez_inventarios.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String code;
  private String name;
  private Float price;

  private Integer stock;

  @ManyToMany(mappedBy = "products")
  private List<Shop> shops;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private List<OrderDetail> orderDetails;

}
