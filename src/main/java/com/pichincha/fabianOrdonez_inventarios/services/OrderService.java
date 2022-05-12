package com.pichincha.fabianOrdonez_inventarios.services;

import com.pichincha.fabianOrdonez_inventarios.services.dtos.OrderDto;

public interface OrderService {
  public OrderDto performPurchase(Long customerId, OrderDto orderDto);
}
