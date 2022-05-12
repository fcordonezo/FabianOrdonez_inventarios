package com.pichincha.fabianOrdonez_inventarios.services.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

  private Long id;

  @JsonProperty("code")
  @JsonAlias("cod")
  private String code;

  private String name;
  private Float price;

  @Positive(message = "'stock' must be greater than 0")
  private Integer stock;

}


