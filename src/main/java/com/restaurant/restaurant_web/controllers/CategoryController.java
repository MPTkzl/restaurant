package com.restaurant.restaurant_web.controllers;

import com.restaurant.restaurant_web.model.CategoryModel;
import com.restaurant.restaurant_web.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @GetMapping("/categories")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String getAllCategories(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "3") int size,
                                   Model model) {
        List<CategoryModel> categories = categoryService.findAllCategory(page, size);
        int totalCategories = categoryService.countCategories();
        int totalPages = (int) Math.ceil((double) totalCategories / size);
        model.addAttribute("categories", categories);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("category", new CategoryModel());
        return "categoryList";
    }

    @PostMapping("/categories/add")
    public String addCategory(@Valid @ModelAttribute("category") CategoryModel category, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("categories", categoryService.findAllCategory(0,3));
            model.addAttribute("currentPage", 0);
            model.addAttribute("totalPages", (int) Math.ceil((double) categoryService.countCategories() / 3));
            return "categoryList";
        }
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    @PostMapping("/categories/update")
    public String updatedCategory(@ModelAttribute("category") CategoryModel category) {
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }

    @PostMapping("/categories/delete")
    public String deleteCategory(@RequestParam Long id, @RequestParam(defaultValue = "false") boolean physical) {
        if (physical) {
            categoryService.deleteCategoryPhysically(id);
        } else {
            categoryService.deleteCategoryLogically(id);
        }
        return "redirect:/categories";
    }


    @GetMapping("/categories/{id}")
    public String getIdCategories(@PathVariable("id") Long id, Model model){
        model.addAttribute( "categories", categoryService.findCategoryById(id));
        return "categoryList";
    }

}
