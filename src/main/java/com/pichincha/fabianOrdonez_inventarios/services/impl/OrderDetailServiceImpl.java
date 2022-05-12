package com.pichincha.fabianOrdonez_inventarios.services.impl;

import com.pichincha.fabianOrdonez_inventarios.entities.OrderDetail;
import com.pichincha.fabianOrdonez_inventarios.repositories.OrderDetailRepository;
import com.pichincha.fabianOrdonez_inventarios.services.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

  private final OrderDetailRepository orderDetailRepository;

  public OrderDetailServiceImpl(
    OrderDetailRepository orderDetailRepository
  ){
    this.orderDetailRepository = orderDetailRepository;
  }


  @Override
  public void addOrderDetail(OrderDetail orderDetail) {
    this.orderDetailRepository.save(orderDetail);
  }
}
