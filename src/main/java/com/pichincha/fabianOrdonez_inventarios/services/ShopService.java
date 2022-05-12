package com.pichincha.fabianOrdonez_inventarios.services;

import com.pichincha.fabianOrdonez_inventarios.services.dtos.ShopDto;

import java.util.List;

public interface ShopService {
  List<ShopDto> getAllShops();
  ShopDto getShopById(Integer id);
  ShopDto addProductToShop(Integer shopId, Long productId);
}
