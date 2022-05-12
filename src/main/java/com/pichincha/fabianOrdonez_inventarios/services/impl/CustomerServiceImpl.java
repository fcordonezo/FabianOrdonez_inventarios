package com.pichincha.fabianOrdonez_inventarios.services.impl;

import com.pichincha.fabianOrdonez_inventarios.entities.Customer;
import com.pichincha.fabianOrdonez_inventarios.exceptions.ResourceNotFoundException;
import com.pichincha.fabianOrdonez_inventarios.repositories.CustomerRepository;
import com.pichincha.fabianOrdonez_inventarios.services.CustomerService;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.CustomerDto;
import com.pichincha.fabianOrdonez_inventarios.services.mappers.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  @Autowired
  public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
    this.customerRepository = customerRepository;
    this.customerMapper = customerMapper;
  }

  @Override
  public CustomerDto getCustomerById(Long id) {

    Customer customer = this.customerRepository
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Customer does not exist"));
    return this.customerMapper.toCustomerDto(customer);

  }

  @Override
  public List<CustomerDto> getAllCustomers() {
    List<Customer> customers = this.customerRepository.findAll();
    return this.customerMapper.toCustomerDtoList(customers);
  }

  @Override
  public CustomerDto createCustomer(CustomerDto customerDto) {
    Customer customer = this.customerMapper.toCustomerEntity(customerDto);
    Customer customerSaved = this.customerRepository.save(customer);
    return this.customerMapper.toCustomerDto(customerSaved);
  }

  @Override
  public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
    Customer customer = this.customerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    this.customerMapper.updateCustomerFromDto(customerDto, customer);
    Customer customerSaved = this.customerRepository.save(customer);
    return this.customerMapper.toCustomerDto(customerSaved);
  }

  @Override
  public void deleteCustomer(Long id) {
    this.customerRepository.deleteById(id);
  }
}
