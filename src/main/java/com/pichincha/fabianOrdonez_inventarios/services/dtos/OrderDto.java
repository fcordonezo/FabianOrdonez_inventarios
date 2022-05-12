package com.pichincha.fabianOrdonez_inventarios.services.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

  private Long id;
  private Date createdAt;

  private Float totalAmount;

  private CustomerDto customer;

  @JsonProperty("orderDetails")
  private List<OrderDetailDto> orderDetails;

}
