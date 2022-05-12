package com.pichincha.fabianOrdonez_inventarios.services.mappers;

import com.pichincha.fabianOrdonez_inventarios.entities.Customer;
import com.pichincha.fabianOrdonez_inventarios.entities.Customer;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.CustomerDto;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.CustomerDto;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CustomerMapper {
  CustomerDto toCustomerDto(Customer customer);

  @Mapping(target = "id", ignore = true)
  Customer toCustomerEntity(CustomerDto customerDto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateCustomerFromDto(CustomerDto customerDto, @MappingTarget Customer customer);

  default List<CustomerDto> toCustomerDtoList(List<Customer> customers){
    if (customers == null) {
      return new ArrayList<>();
    }
    return customers.parallelStream().map(this::toCustomerDto).collect(Collectors.toList());
  }
}
