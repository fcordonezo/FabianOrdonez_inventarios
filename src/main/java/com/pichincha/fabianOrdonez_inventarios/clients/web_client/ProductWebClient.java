package com.pichincha.fabianOrdonez_inventarios.clients.web_client;

import com.pichincha.fabianOrdonez_inventarios.services.dtos.ProductDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductWebClient {

  private static final String URI = "/v1/9d24a44e-1447-43c3-8651-5b8f6b86ea3c";
  private static final String BASE_URL = "https://mocki.io";

  private final WebClient webClient = WebClient.create(BASE_URL);

  public List<ProductDto> retrieveProducts() {
    return webClient.get().uri(URI)
        .retrieve()
        .bodyToFlux(ProductDto.class)
        .collectList()
        .block();
  }
}
