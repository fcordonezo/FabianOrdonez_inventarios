package com.pichincha.fabianOrdonez_inventarios.services.dtos;

import com.pichincha.fabianOrdonez_inventarios.entities.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto {

  private Long id;
  private String code;
  private String name;
  private List<ProductDto> products;

}
