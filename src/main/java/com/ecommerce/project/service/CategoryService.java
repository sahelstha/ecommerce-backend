package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;

import javax.lang.model.element.NestingKind;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);
    String deleteCategory(Long categoryId);
}
