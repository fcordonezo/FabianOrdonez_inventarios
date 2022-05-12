package com.pichincha.fabianOrdonez_inventarios.services;

import com.pichincha.fabianOrdonez_inventarios.services.dtos.ProductDto;

import java.util.List;

public interface ProductService {

  ProductDto getProductById(Long id);
  List<ProductDto> getAllProducts();
  ProductDto createProduct(ProductDto productDto);
  List<ProductDto> createProducts(List<ProductDto> productsDto);
  ProductDto updateProduct(Long id, ProductDto productDto);
  ProductDto patchProductStock(Long id, ProductDto productDto);
  void deleteProduct(Long id);
}
