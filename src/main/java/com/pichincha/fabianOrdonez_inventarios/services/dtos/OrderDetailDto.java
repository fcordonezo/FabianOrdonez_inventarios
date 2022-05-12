package com.pichincha.fabianOrdonez_inventarios.services.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {

  private Long id;

  private Integer amount;
  private Long productId;
  private Integer shopId;

}
