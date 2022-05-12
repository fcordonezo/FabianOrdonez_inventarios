package com.pichincha.fabianOrdonez_inventarios.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "shops")
public class Shop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String code;
  private String name;

  @ManyToMany
  @JoinTable(
      name = "shops_have_products",
      joinColumns = @JoinColumn(name = "shop_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id")
  )
  private List<Product> products;

  @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
  private List<OrderDetail> orderDetails;
}
