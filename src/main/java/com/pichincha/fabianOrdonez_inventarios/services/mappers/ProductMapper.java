package com.pichincha.fabianOrdonez_inventarios.services.mappers;

import com.pichincha.fabianOrdonez_inventarios.entities.Product;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.ProductDto;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ProductMapper {

  ProductDto toProductDto(Product product);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "stock", source = "productDto.stock", defaultValue = "0")
  Product toProductEntity(ProductDto productDto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateProductFromDto(ProductDto productDto, @MappingTarget Product product);

  @Mapping(target = "stock", source = "stock")
  void updateProductStock(Integer stock, @MappingTarget Product product);

  default List<ProductDto> toProductDtoList(List<Product> products){
    if (products == null) {
      return new ArrayList<>();
    }
    return products.parallelStream().map(this::toProductDto).collect(Collectors.toList());
  }

  default List<Product> toProductEntityList(List<ProductDto> productsDto){
    if (productsDto == null) {
      return new ArrayList<>();
    }
    return productsDto.parallelStream().map(this::toProductEntity).collect(Collectors.toList());
  }
}
