package com.restaurant.restaurant_web.service;

import com.restaurant.restaurant_web.model.CategoryModel;
import com.restaurant.restaurant_web.repository.CategoryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryCategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    public InMemoryCategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public int countCategories(){
        return (int)categoryRepository.count();
    }

    @Override
    public List<CategoryModel> findAllCategory(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return categoryRepository.findAll(pageable).getContent();
    }

    @Override
    public List<CategoryModel> listAllCategory() {
        return categoryRepository.findAll();
    }
    @Override
    public CategoryModel findCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public CategoryModel addCategory(CategoryModel category){
        return categoryRepository.save(category);
    }

    @Override
    public CategoryModel updateCategory(CategoryModel category){
        if (categoryRepository.existsById(category.getId())){
            return categoryRepository.save(category);
        }
        return null;
    }

    @Override
    public void deleteCategoryLogically(Long id) {
        CategoryModel category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            category.setDeleted(true);
            categoryRepository.save(category);
        }
    }

    @Override
    public void deleteCategoryPhysically(Long id) {
        categoryRepository.deleteById(id);
    }

}
