package com.fashionstore.dao;

import com.fashionstore.model.Category;
import java.util.List;

public interface CategoryDAO {

    int save(Category category);

    Category findById(int categoryId);

    List<Category> findAll();

    List<Category> findActive();

    boolean update(Category category);

    boolean delete(int categoryId);
}