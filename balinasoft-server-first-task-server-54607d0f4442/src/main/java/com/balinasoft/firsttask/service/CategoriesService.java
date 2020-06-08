package com.balinasoft.firsttask.service;

import com.balinasoft.firsttask.dto.CategoryDtoIn;
import com.balinasoft.firsttask.dto.CategoryDtoOut;

import java.util.List;

public interface CategoriesService {

    List<CategoryDtoOut> getCategories();

    CategoryDtoOut addCategory(CategoryDtoIn category);

    void deleteCategory(int categoryId);
}
