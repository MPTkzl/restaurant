package com.restaurant.restaurant_web.service;

import com.restaurant.restaurant_web.model.MenuModel;
import com.restaurant.restaurant_web.repository.MenuRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryMenuServiceImpl implements MenuService{

    private final MenuRepository menuRepository;

    public InMemoryMenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public int countMenus(){
        return (int)menuRepository.count();
    }

    @Override
    public List<MenuModel> findAllMenu(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return menuRepository.findAll(pageable).getContent();
    }

    @Override
    public List<MenuModel> listAllMenu() {
        return menuRepository.findAll();
    }
    @Override
    public MenuModel findMenuById(Long id) {
        return menuRepository.findById(id).orElse(null);
    }

    @Override
    public MenuModel addMenu(MenuModel menu){
        return menuRepository.save(menu);
    }

    @Override
    public MenuModel updateMenu(MenuModel menu){
        if (menuRepository.existsById(menu.getId())){
            return menuRepository.save(menu);
        }
        return null;
    }

    @Override
    public void deleteMenuLogically(Long id) {
        MenuModel menu = menuRepository.findById(id).orElse(null);
        if (menu != null) {
            menu.setDeleted(true);
            menuRepository.save(menu);
        }
    }

    @Override
    public void deleteMenuPhysically(Long id) {
        menuRepository.deleteById(id);
    }


}
