package com.pichincha.fabianOrdonez_inventarios.services.impl;

import com.pichincha.fabianOrdonez_inventarios.entities.*;
import com.pichincha.fabianOrdonez_inventarios.exceptions.ResourceNotFoundException;
import com.pichincha.fabianOrdonez_inventarios.repositories.CustomerRepository;
import com.pichincha.fabianOrdonez_inventarios.repositories.OrderRepository;
import com.pichincha.fabianOrdonez_inventarios.repositories.ProductRepository;
import com.pichincha.fabianOrdonez_inventarios.repositories.ShopRepository;
import com.pichincha.fabianOrdonez_inventarios.services.OrderDetailService;
import com.pichincha.fabianOrdonez_inventarios.services.OrderService;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.OrderDetailDto;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.OrderDto;
import com.pichincha.fabianOrdonez_inventarios.services.mappers.OrderMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderMapper orderMapper;
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final CustomerRepository customerRepository;
  private final ShopRepository shopRepository;
  private final OrderDetailService orderDetailService;

  public OrderServiceImpl(
    OrderMapper orderMapper,
    OrderRepository orderRepository,
    ProductRepository productRepository,
    CustomerRepository customerRepository,
    ShopRepository shopRepository,
    OrderDetailService orderDetailService
  ) {
    this.orderMapper = orderMapper;
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.customerRepository = customerRepository;
    this.shopRepository = shopRepository;
    this.orderDetailService = orderDetailService;
  }

  @Override
  public OrderDto performPurchase(Long customerId, OrderDto orderDto) {

    Customer customer = this.customerRepository
      .findById(customerId)
      .orElseThrow(ResourceNotFoundException::new);

    List<OrderDetail> orderDetails = new ArrayList<>();
    for (OrderDetailDto orderDetailDto : orderDto.getOrderDetails()) {
      Shop shop = this.shopRepository.findById(orderDetailDto.getShopId()).orElseThrow(ResourceNotFoundException::new);
      Product product = this.productRepository.findById(orderDetailDto.getProductId()).orElseThrow(ResourceNotFoundException::new);
      OrderDetail orderDetail = new OrderDetail();
      orderDetail.setShop(shop);
      orderDetail.setProduct(product);
      orderDetail.setAmount(orderDetailDto.getAmount());
      orderDetails.add(orderDetail);
    }

    Order order = Order.builder().orderDetails(orderDetails).customer(customer).build();

    this.orderRepository.save(order);

    return this.orderMapper.toOrderDto(order);
  }

}
