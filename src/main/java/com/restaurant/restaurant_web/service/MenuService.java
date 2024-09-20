package com.restaurant.restaurant_web.service;

import com.restaurant.restaurant_web.model.MenuModel;

import java.util.List;

public interface MenuService {
    public List<MenuModel> findAllMenu(int page, int size);
    public List<MenuModel> listAllMenu();
    public int countMenus();
    public MenuModel findMenuById(Long id);
    public MenuModel addMenu(MenuModel menu);
    public MenuModel updateMenu(MenuModel menu);
    public void deleteMenuLogically(Long id);
    public void deleteMenuPhysically(Long id);
}
