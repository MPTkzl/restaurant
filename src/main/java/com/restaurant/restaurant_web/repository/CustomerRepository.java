package com.restaurant.restaurant_web.repository;

import com.restaurant.restaurant_web.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
}
