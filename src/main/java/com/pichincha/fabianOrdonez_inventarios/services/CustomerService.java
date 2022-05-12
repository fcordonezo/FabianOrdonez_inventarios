package com.pichincha.fabianOrdonez_inventarios.services;

import com.pichincha.fabianOrdonez_inventarios.services.dtos.CustomerDto;

import java.util.List;

public interface CustomerService {

  CustomerDto getCustomerById(Long id);
  List<CustomerDto> getAllCustomers();
  CustomerDto createCustomer(CustomerDto customerDto);
  CustomerDto updateCustomer(Long id, CustomerDto customerDto);
  void deleteCustomer(Long id);
}
