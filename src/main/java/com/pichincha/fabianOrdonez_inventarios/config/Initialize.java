package com.pichincha.fabianOrdonez_inventarios.config;

import com.pichincha.fabianOrdonez_inventarios.clients.web_client.ProductWebClient;
import com.pichincha.fabianOrdonez_inventarios.services.ProductService;
import com.pichincha.fabianOrdonez_inventarios.services.dtos.ProductDto;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class Initialize{

  private final ProductWebClient productWebClient;

  private final ProductService productService;

  public Initialize(ProductService productService){
    this.productService = productService;
    this.productWebClient = new ProductWebClient();
  }

  @PostConstruct
  public void afterPropertiesSet() {
    this.getProductsFromExternalAPI();
  }

  private void getProductsFromExternalAPI(){
    List<ProductDto> productsDto = this.productWebClient.retrieveProducts();
    this.productService.createProducts(productsDto);
  }
}
