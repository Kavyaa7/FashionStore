package com.fashionstore.service.impl;

import com.fashionstore.dao.CategoryDAO;
import com.fashionstore.dao.impl.CategoryDAOImpl;
import com.fashionstore.model.Category;
import com.fashionstore.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryServiceImpl() {
        categoryDAO = new CategoryDAOImpl();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }
}