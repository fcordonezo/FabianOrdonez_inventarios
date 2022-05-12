package com.pichincha.fabianOrdonez_inventarios.repositories;

import com.pichincha.fabianOrdonez_inventarios.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
