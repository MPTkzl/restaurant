package com.restaurant.restaurant_web.controllers;

import com.restaurant.restaurant_web.model.MenuModel;
import com.restaurant.restaurant_web.service.MenuService;
import com.restaurant.restaurant_web.service.CategoryService;
import com.restaurant.restaurant_web.service.TableService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TableService tableService;

    @GetMapping("/menus")
    public String getAllMenus(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "3") int size,
                              Model model) {
        List<MenuModel> menus = menuService.findAllMenu(page, size);
        int totalMenus = menuService.countMenus();
        int totalPages = (int) Math.ceil((double) totalMenus / size);
        model.addAttribute("menus", menus);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("menu", new MenuModel());
        model.addAttribute("categories", categoryService.listAllCategory());
        model.addAttribute("tables", tableService.listAllTable());
        return "menuList";
    }

    @PostMapping("/menus/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    public String addMenu(@Valid @ModelAttribute("menu") MenuModel menu, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("menus", menuService.findAllMenu(0,3));
            model.addAttribute("currentPage", 0);
            model.addAttribute("totalPages", (int) Math.ceil((double) menuService.countMenus() / 3));
            model.addAttribute("categories", categoryService.listAllCategory());
            model.addAttribute("tables", tableService.listAllTable());
            return "menuList";
        }
        menuService.addMenu(menu);
        return "redirect:/menus";
    }

    @PostMapping("/menus/update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    public String updateMenu(@ModelAttribute("menu") MenuModel menu) {
        menuService.updateMenu(menu);
        return "redirect:/menus";
    }

    @PostMapping("/menus/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    public String deleteMenu(@RequestParam Long id, @RequestParam(defaultValue = "false") boolean physical) {
        if (physical) {
            menuService.deleteMenuPhysically(id);
        } else {
            menuService.deleteMenuLogically(id);
        }
        return "redirect:/menus";
    }

    @GetMapping("/menus/{id}")
    public String getIdMenus(@PathVariable("id") Long id, Model model){
        model.addAttribute("menu", menuService.findMenuById(id));
        model.addAttribute("categories", categoryService.listAllCategory());
        model.addAttribute("tables", tableService.listAllTable());
        return "menuList";
    }

}
