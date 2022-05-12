package com.pichincha.fabianOrdonez_inventarios.services.impl;

import com.pichincha.fabianOrdonez_inventarios.entities.Product;
import com.pichincha.fabianOrdonez_inventarios.entities.Shop;
import com.pichincha.fabianOrdonez_inventarios.exceptions.ConflictException;
import com.pichincha.fabianOrdonez_inventarios.exceptions.ResourceNotFoundException;
import com.pichincha.fabianOrdonez_inventarios.repositories.ShopRepository;
import com.pichincha.fabianOrdonez_inventarios.services.ProductService;
import com.pichincha.fabianOrdonez_inventarios.services.ShopService;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.ProductDto;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.ShopDto;
import com.pichincha.fabianOrdonez_inventarios.services.mappers.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

  private final ShopRepository shopRepository;
  private final ShopMapper shopMapper;
  private final ProductService productService;

  @Autowired
  public ShopServiceImpl(ShopRepository shopRepository, ShopMapper shopMapper, ProductService productService) {
    this.shopRepository = shopRepository;
    this.shopMapper = shopMapper;
    this.productService = productService;
  }

  @Override
  public ShopDto getShopById(Integer id) {
    Shop shop = this.shopRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    return this.shopMapper.toShopDto(shop);
  }

  @Override
  public ShopDto addProductToShop(Integer shopId, Long productId) {

    Shop shop = this.shopRepository.findById(shopId).orElseThrow(ResourceNotFoundException::new);
    ProductDto productDto = this.productService.getProductById(productId);
    ShopDto shopDto = this.shopMapper.toShopDto(shop);

    if (shopDto.getProducts().stream().anyMatch(product -> product.getId().equals(productId))) {
      throw new ConflictException("Product already exists on selected shop");
    }
    shopDto.getProducts().add(productDto);
    this.shopMapper.updateShopFromDto(shopDto, shop);
    Shop shopSaved = this.shopRepository.save(shop);
    return this.shopMapper.toShopDto(shopSaved);
  }

  @Override
  public List<ShopDto> getAllShops() {
    List<Shop> shops = this.shopRepository.findAll();
    return this.shopMapper.toShopDtoList(shops);
  }
}
