package com.balinasoft.firsttask.repository;

import com.balinasoft.firsttask.domain.Category;
import com.balinasoft.firsttask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByUser(User user);
}
