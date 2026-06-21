package com.fashionstore.service.impl;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.model.Product;
import com.fashionstore.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl() {
        productDAO = new ProductDAOImpl();
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }
    
    @Override
    public Product findById(int productId) {
        return productDAO.findById(productId);
    }
    
    @Override
    public List<Product> filterProducts(
            String categoryId,
            String priceRange
    ) {

        return productDAO.filterProducts(
                categoryId,
                priceRange
        );
    }
    
    @Override
    public List<Product> searchFilterSortProducts(
            String categoryId,
            String priceRange,
            String keyword,
            String sortBy
    ) {

        return productDAO.searchFilterSortProducts(
                categoryId,
                priceRange,
                keyword,
                sortBy
        );
    }

    @Override
    public boolean reduceStock(int productId, int quantity) {

        return productDAO.reduceStock(
                productId,
                quantity
        );
    }
}