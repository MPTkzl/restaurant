package com.restaurant.restaurant_web.repository;

import com.restaurant.restaurant_web.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {

}
