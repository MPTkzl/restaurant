package com.restaurant.restaurant_web.repository;

import com.restaurant.restaurant_web.model.WaiterModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaiterRepository extends JpaRepository<WaiterModel, Long> {
}
