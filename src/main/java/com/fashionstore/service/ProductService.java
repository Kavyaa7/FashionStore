package com.fashionstore.service;

import com.fashionstore.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product findById(int productId);
    List<Product> filterProducts(
            String categoryId,
            String priceRange
    );
    
    List<Product> searchFilterSortProducts(
            String categoryId,
            String priceRange,
            String keyword,
            String sortBy
    );
    
    boolean reduceStock(int productId, int quantity);
}
