package com.restaurant.restaurant_web.repository;

import com.restaurant.restaurant_web.model.TableModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<TableModel, Long> {
}
