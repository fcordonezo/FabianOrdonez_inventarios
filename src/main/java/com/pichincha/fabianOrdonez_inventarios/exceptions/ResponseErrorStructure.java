package com.pichincha.fabianOrdonez_inventarios.exceptions;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseErrorStructure {
  private final String title;
  private final String message;
  private final String exceptionCode;
}
