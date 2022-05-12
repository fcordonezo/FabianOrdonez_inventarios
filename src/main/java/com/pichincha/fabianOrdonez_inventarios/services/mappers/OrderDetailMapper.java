package com.pichincha.fabianOrdonez_inventarios.services.mappers;

import com.pichincha.fabianOrdonez_inventarios.entities.OrderDetail;
import com.pichincha.fabianOrdonez_inventarios.entities.Product;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.OrderDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper
public interface OrderDetailMapper {

  OrderDetailDto toOrderDetailDto(OrderDetail orderDetail);

  @Mapping(target = "id", ignore = true)
  OrderDetail toOrderDetailEntity(OrderDetailDto orderDetailDto);

  default List<OrderDetailDto> toOrderDetailDtoList(List<OrderDetail> orderDetails) {
    if (orderDetails == null) {
      return new ArrayList<>();
    }
    return orderDetails.parallelStream().map(this::toOrderDetailDto).collect(Collectors.toList());
  }

  default List<OrderDetail> toOrderDetailEntityList(List<OrderDetailDto> orderDetailsDto) {
    if (orderDetailsDto == null) {
      return new ArrayList<>();
    }
    return orderDetailsDto.parallelStream().map(this::toOrderDetailEntity).collect(Collectors.toList());
  }

}
