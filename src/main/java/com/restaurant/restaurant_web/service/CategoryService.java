package com.restaurant.restaurant_web.service;

import com.restaurant.restaurant_web.model.CategoryModel;

import java.util.List;

public interface CategoryService {

    public List<CategoryModel> findAllCategory(int page, int size);
    public List<CategoryModel> listAllCategory();
    public int countCategories();
    public CategoryModel findCategoryById(Long id);
    public CategoryModel addCategory(CategoryModel category);
    public CategoryModel updateCategory(CategoryModel category);
    public void deleteCategoryLogically(Long id);
    public void deleteCategoryPhysically(Long id);
}
