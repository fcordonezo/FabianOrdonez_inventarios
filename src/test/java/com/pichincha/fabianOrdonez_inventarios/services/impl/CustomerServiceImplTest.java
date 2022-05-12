package com.pichincha.fabianOrdonez_inventarios.services.impl;

import com.pichincha.fabianOrdonez_inventarios.entities.Customer;
import com.pichincha.fabianOrdonez_inventarios.exceptions.ResourceNotFoundException;
import com.pichincha.fabianOrdonez_inventarios.repositories.CustomerRepository;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.CustomerDto;
import com.pichincha.fabianOrdonez_inventarios.services.mappers.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@DisplayName("Customer service")
class CustomerServiceImplTest {

  @InjectMocks
  private CustomerServiceImpl customerService;

  @Mock
  private CustomerRepository customerRepository;

  @Mock
  private CustomerMapper customerMapper;

  private Customer customer;
  private CustomerDto customerDto;

  @BeforeEach
  void setUp() {
    openMocks(this);

    customer = Customer.builder()
      .id(1L)
      .identification(12345L)
      .name("TEST NAME")
      .build();

    customerDto = CustomerDto.builder()
      .id(1L)
      .identification(12345L)
      .name("TEST NAME")
      .build();
  }

  @Test
  @DisplayName("Get customer by id: Throws ResourceNotFoundException")
  void getCustomerById() {
    when(customerRepository.findById(any(Long.class))).thenThrow(new ResourceNotFoundException());
    assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomerById(10L));
  }

  @Test
  @DisplayName("Get all customers: Fields are ok")
  void getAllCustomers_ok() {
    when(customerRepository.findAll()).thenReturn(Collections.singletonList(customer));
    when(customerMapper.toCustomerDtoList(any(List.class))).thenReturn(Collections.singletonList(customerDto));
    List<CustomerDto> customersDto = customerService.getAllCustomers();
    assertNotNull(customerService.getAllCustomers());
    assertEquals(customersDto.get(0).getId(), customer.getId());
    assertEquals(customersDto.get(0).getIdentification(), customer.getIdentification());
    assertEquals(customersDto.get(0).getName(), customer.getName());
  }

  @Test
  @DisplayName("Get all customers: Fields are not ok")
  void getAllCustomers_fail() {
    when(customerRepository.findAll()).thenReturn(Collections.singletonList(customer));
    when(customerMapper.toCustomerDtoList(any(List.class))).thenReturn(Collections.singletonList(new CustomerDto()));
    List<CustomerDto> customersDto = customerService.getAllCustomers();
    assertNotEquals(customersDto.get(0).getId(), customer.getId());
    assertNotEquals(customersDto.get(0).getIdentification(), customer.getIdentification());
    assertNotEquals(customersDto.get(0).getName(), customer.getName());
  }

  @Test
  @DisplayName("Create customer: Fields are ok")
  void createCustomer_ok() {
    when(customerMapper.toCustomerEntity(customerDto)).thenReturn(customer);
    when(customerRepository.save(customer)).thenReturn(customer);
    when(customerMapper.toCustomerDto(customer)).thenReturn(customerDto);
    CustomerDto customerDtoReturned = this.customerService.createCustomer(customerDto);
    assertNotNull(customerDtoReturned);
    assertEquals(customerDtoReturned.getId(), customerDto.getId());
    assertEquals(customerDtoReturned.getName(), customerDto.getName());
    assertEquals(customerDtoReturned.getIdentification(), customerDto.getIdentification());
  }

  @Test
  @DisplayName("Create customer: Fields are ok")
  void createCustomer_fail() {
    when(customerMapper.toCustomerEntity(customerDto)).thenReturn(customer);
    when(customerRepository.save(customer)).thenReturn(customer);
    when(customerMapper.toCustomerDto(customer)).thenReturn(new CustomerDto());
    CustomerDto customerDtoReturned = this.customerService.createCustomer(customerDto);
    assertNotEquals(customerDtoReturned.getId(), customerDto.getId());
    assertNotEquals(customerDtoReturned.getName(), customerDto.getName());
    assertNotEquals(customerDtoReturned.getIdentification(), customerDto.getIdentification());
  }
}