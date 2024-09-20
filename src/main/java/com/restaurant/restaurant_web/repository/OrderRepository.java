package com.restaurant.restaurant_web.repository;

import com.restaurant.restaurant_web.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
