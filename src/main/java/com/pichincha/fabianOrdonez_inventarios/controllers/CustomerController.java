package com.pichincha.fabianOrdonez_inventarios.controllers;

import com.pichincha.fabianOrdonez_inventarios.services.CustomerService;
import com.pichincha.fabianOrdonez_inventarios.services.OrderService;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.CustomerDto;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerService customerService;
  private final OrderService orderService;

  @Autowired
  public CustomerController(
    CustomerService customerService,
    OrderService orderService
  ) {
    this.customerService = customerService;
    this.orderService = orderService;
  }

  @GetMapping("")
  public ResponseEntity<List<CustomerDto>> getAllCustomers() {
    List<CustomerDto> customerDtoList = this.customerService.getAllCustomers();
    return ResponseEntity.ok(customerDtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long id) {
    CustomerDto customerDto = this.customerService.getCustomerById(id);
    return ResponseEntity.ok(customerDto);
  }

  @PostMapping("")
  public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
    CustomerDto customerDtoSaved = this.customerService.createCustomer(customerDto);
    URI location = URI.create(String.format("/customers/%s", customerDtoSaved.getId()));
    return ResponseEntity.created(location).build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CustomerDto> updateCustomer(
      @PathVariable("id") Long id,
      @RequestBody CustomerDto customerDto
  ) {
    CustomerDto customerDtoSaved = this.customerService.updateCustomer(id, customerDto);
    return ResponseEntity.ok(customerDtoSaved);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
    this.customerService.deleteCustomer(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{id}/order")
  public ResponseEntity<OrderDto> PerformPurchase(
    @PathVariable("id") Long id,
    @Valid @RequestBody OrderDto orderDto
  ) {
    OrderDto orderDtoReturned = this.orderService.performPurchase(id, orderDto);
    return ResponseEntity.ok(orderDtoReturned);
  }
}