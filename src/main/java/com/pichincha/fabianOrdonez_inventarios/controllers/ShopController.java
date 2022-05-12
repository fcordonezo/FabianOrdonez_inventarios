package com.pichincha.fabianOrdonez_inventarios.controllers;

import com.pichincha.fabianOrdonez_inventarios.services.ShopService;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.ShopDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

  private final ShopService shopService;

  @Autowired
  public ShopController(ShopService shopService) {
    this.shopService = shopService;
  }

  @GetMapping("")
  public ResponseEntity<List<ShopDto>> getAllShops() {
    List<ShopDto> shopDtoList = this.shopService.getAllShops();
    return ResponseEntity.ok(shopDtoList);
  }

  @PostMapping("/{shop-id}/products/{product-id}")
  public ResponseEntity<ShopDto> addProductToShop(
      @PathVariable("shop-id") Integer shopId,
      @PathVariable("product-id") Long productId
  ) {
    ShopDto shopDto = this.shopService.addProductToShop(shopId, productId);
    return ResponseEntity.ok(shopDto);
  }
}
