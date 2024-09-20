package com.restaurant.restaurant_web.repository;

import com.restaurant.restaurant_web.model.MenuModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuModel, Long> {

}
