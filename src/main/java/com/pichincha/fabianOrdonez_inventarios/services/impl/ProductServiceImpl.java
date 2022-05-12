package com.pichincha.fabianOrdonez_inventarios.services.impl;

import com.pichincha.fabianOrdonez_inventarios.entities.Product;
import com.pichincha.fabianOrdonez_inventarios.exceptions.ResourceNotFoundException;
import com.pichincha.fabianOrdonez_inventarios.repositories.ProductRepository;
import com.pichincha.fabianOrdonez_inventarios.services.ProductService;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.ProductDto;
import com.pichincha.fabianOrdonez_inventarios.services.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  @Override
  public ProductDto getProductById(Long id) {
    Product product = this.productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    return this.productMapper.toProductDto(product);
  }

  @Override
  public List<ProductDto> getAllProducts() {
    List<Product> products = this.productRepository.findAll();
    return this.productMapper.toProductDtoList(products);
  }

  @Override
  public ProductDto createProduct(ProductDto productDto) {
    Product product = this.productMapper.toProductEntity(productDto);
    Product productSaved = this.productRepository.save(product);
    return this.productMapper.toProductDto(productSaved);
  }

  @Override
  public List<ProductDto> createProducts(List<ProductDto> productsDto) {
    List<Product> productsFromDB = this.productRepository.findAll();
    List<Product> productsFromAPI = this.productMapper.toProductEntityList(productsDto);
    List<Product> productsSaved = productsFromAPI.stream()
      .filter(product ->
        productsFromDB.stream()
          .noneMatch(productDtoFromDB ->
            product.getId().equals(productDtoFromDB.getId())
          )
      )
      .peek(this.productRepository::save)
      .collect(Collectors.toList());
    return this.productMapper.toProductDtoList(productsSaved);
  }

  @Override
  public ProductDto updateProduct(Long id, ProductDto productDto) {
    Product product = this.productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    this.productMapper.updateProductFromDto(productDto, product);
    Product productSaved = this.productRepository.save(product);
    return this.productMapper.toProductDto(productSaved);
  }

  @Override
  public ProductDto patchProductStock(Long id, ProductDto productDto) {
    Product product = this.productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    this.productMapper.updateProductStock(productDto.getStock(), product);
    Product productSaved = this.productRepository.save(product);
    return this.productMapper.toProductDto(productSaved);
  }

  @Override
  public void deleteProduct(Long id) {
    this.productRepository.deleteById(id);
  }
}
