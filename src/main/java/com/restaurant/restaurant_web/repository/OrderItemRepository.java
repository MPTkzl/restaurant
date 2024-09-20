package com.restaurant.restaurant_web.repository;

import com.restaurant.restaurant_web.model.OrderItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemModel, Long> {
}
