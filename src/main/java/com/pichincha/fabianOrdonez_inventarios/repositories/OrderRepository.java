package com.pichincha.fabianOrdonez_inventarios.repositories;

import com.pichincha.fabianOrdonez_inventarios.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
