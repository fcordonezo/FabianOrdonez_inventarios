package com.pichincha.fabianOrdonez_inventarios.repositories;

import com.pichincha.fabianOrdonez_inventarios.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
