package com.pichincha.fabianOrdonez_inventarios.controllers;

import com.pichincha.fabianOrdonez_inventarios.services.ProductService;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("")
  public ResponseEntity<List<ProductDto>> getAllProducts() {
    List<ProductDto> productDtoList = this.productService.getAllProducts();
    return ResponseEntity.ok(productDtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
    ProductDto productDto = this.productService.getProductById(id);
    return ResponseEntity.ok(productDto);
  }

  @PostMapping("")
  public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
    ProductDto productDtoSaved = this.productService.createProduct(productDto);
    URI location = URI.create(String.format("/products/%s", productDtoSaved.getId()));
    return ResponseEntity.created(location).build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ProductDto> updateProduct(
      @PathVariable("id") Long id,
      @Valid @RequestBody ProductDto productDto
  ) {
    ProductDto productDtoSaved = this.productService.updateProduct(id, productDto);
    return ResponseEntity.ok(productDtoSaved);
  }

  @PatchMapping("/{id}/stock")
  public ResponseEntity<ProductDto> patchProductStock(
      @PathVariable("id") Long id,
      @Valid @RequestBody ProductDto productDto
  ) {
    ProductDto productDtoSaved = this.productService.patchProductStock(id, productDto);
    return ResponseEntity.ok(productDtoSaved);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
    this.productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}
