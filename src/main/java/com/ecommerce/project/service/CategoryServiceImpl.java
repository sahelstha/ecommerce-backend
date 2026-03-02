package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        List<Category> categories = categoryRepository.findAll();

        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        categoryRepository.delete(category);
        return "Category with categoryId: " + categoryId + "deleted successfully!";
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        List<Category> categories = categoryRepository.findAll();

        Optional<Category> optionalCategory = categories.stream()
                .filter(category1 -> category1.getCategoryId().equals(id))
                .findFirst();
        if(optionalCategory.isPresent()){
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return categoryRepository.save(existingCategory);
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
        }
    }
}
