package com.restaurant.restaurant_web.repository;

import com.restaurant.restaurant_web.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentModel, Long> {
}
