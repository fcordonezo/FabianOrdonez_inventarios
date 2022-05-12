package com.pichincha.fabianOrdonez_inventarios.services.dtos;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

  private Long id;

  @NotBlank(message = "'name' field cannot be empty or null")
  private String name;

  @Positive(message = "A valid 'identification' cannot be less or equal to zero")
  private Long identification;

  private Byte[] Photo;

}