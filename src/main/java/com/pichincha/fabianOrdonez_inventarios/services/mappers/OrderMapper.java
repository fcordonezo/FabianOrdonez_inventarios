package com.pichincha.fabianOrdonez_inventarios.services.mappers;

import com.pichincha.fabianOrdonez_inventarios.entities.Order;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface OrderMapper {

  OrderDto toOrderDto(Order order);

  @Mapping(target = "id", ignore = true)
  Order toOrderEntity(OrderDto orderDto);

  default List<OrderDto> toOrderDtoList(List<Order> orders){
    if (orders == null) {
      return new ArrayList<>();
    }
    return orders.parallelStream().map(this::toOrderDto).collect(Collectors.toList());
  }

  default List<Order> toOrderEntityList(List<OrderDto> ordersDto){
    if (ordersDto == null) {
      return new ArrayList<>();
    }
    return ordersDto.parallelStream().map(this::toOrderEntity).collect(Collectors.toList());
  }
}
