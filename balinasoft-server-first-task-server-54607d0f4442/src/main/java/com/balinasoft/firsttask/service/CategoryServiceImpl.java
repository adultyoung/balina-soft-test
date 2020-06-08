package com.balinasoft.firsttask.service;

import com.balinasoft.firsttask.domain.Category;
import com.balinasoft.firsttask.domain.User;
import com.balinasoft.firsttask.dto.CategoryDtoIn;
import com.balinasoft.firsttask.dto.CategoryDtoOut;
import com.balinasoft.firsttask.repository.CategoryRepository;
import com.balinasoft.firsttask.repository.UserRepository;
import com.balinasoft.firsttask.system.error.ApiAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.balinasoft.firsttask.util.SecurityContextHolderWrapper.currentUserId;

@Service
public class CategoryServiceImpl implements CategoriesService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CategoryDtoOut> getCategories() {
        List<Category> categories = categoryRepository.findAllByUser(userRepository.getOne(currentUserId()));
        return categories.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDtoOut addCategory(CategoryDtoIn category) {
        User user = userRepository.getOne(currentUserId());
        Category entity = new Category();
        entity.setUser(user);
        entity.setCategory(category.getCategory());
        entity = categoryRepository.save(entity);
        return toDto(entity);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category = categoryRepository.getOne(categoryId);
        ApiAssert.notFound(category == null);
        ApiAssert.forbidden(category.getUser().getId() != currentUserId());
        categoryRepository.delete(categoryId);

    }

    private CategoryDtoOut toDto(Category category) {
        return new CategoryDtoOut(category.getId(),category.getCategory());
    }
}
