package com.pichincha.fabianOrdonez_inventarios.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.pichincha.fabianOrdonez_inventarios.services.CustomerService;
import com.pichincha.fabianOrdonez_inventarios.services.OrderService;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.CustomerDto;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Customer controller")
class CustomerControllerTest {

  @InjectMocks
  private CustomerController customerController;

  @Mock
  private CustomerService customerService;

  @Mock
  private OrderService orderService;

  private MockMvc mockMvc;
  private String requestJson;

  private CustomerDto customerDto;
  private ObjectWriter objectWriter;


  @BeforeEach
  void setUp() {

    openMocks(this);

    customerDto = CustomerDto.builder()
      .id(1L)
      .identification(12345L)
      .name("TEST NAME")
      .build();

    mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
  }

  @Test
  @DisplayName("Get all customers: OK")
  void getAllCustomers_ok() throws Exception {

    requestJson = objectWriter.writeValueAsString(customerDto);

    when(customerService.getAllCustomers()).thenReturn(Collections.singletonList(customerDto));

    mockMvc.perform(
      get("/customers")
      .contentType(MediaType.APPLICATION_JSON)
      .content(requestJson)
      .characterEncoding("utf-8")
    )
      .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Get customer by id: OK")
  void getCustomerById_ok() throws Exception {
    requestJson = objectWriter.writeValueAsString(customerDto);
    when(customerService.getCustomerById(any(Long.class))).thenReturn(new CustomerDto());
    mockMvc.perform(
      get("/customers")
        .param("id", "1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestJson)
        .characterEncoding("utf-8")
    )
      .andExpect(status().isOk());
  }

}