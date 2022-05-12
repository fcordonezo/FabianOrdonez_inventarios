package com.pichincha.fabianOrdonez_inventarios.services.mappers;

import com.pichincha.fabianOrdonez_inventarios.entities.Shop;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.ShopDto;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ShopMapper {

  ShopDto toShopDto(Shop shop);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateShopFromDto(ShopDto shopDto, @MappingTarget Shop shop);

  default List<ShopDto> toShopDtoList(List<Shop> shops){
    if (shops == null) {
      return new ArrayList<>();
    }
    return shops.parallelStream().map(this::toShopDto).collect(Collectors.toList());
  }
}
