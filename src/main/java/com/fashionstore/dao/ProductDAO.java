package com.fashionstore.dao;

import com.fashionstore.model.Product;
import java.math.BigDecimal;
import java.util.List;

public interface ProductDAO {

    int save(Product product);

    Product findById(int productId);

    List<Product> findAll();

    List<Product> findActive();

    List<Product> findByCategory(int categoryId);

    List<Product> searchByName(String keyword);

    List<Product> filterProducts(Integer categoryId,
                                 BigDecimal minPrice,
                                 BigDecimal maxPrice,
                                 Boolean isActive);

    boolean update(Product product);

    boolean delete(int productId);
    
    List<Product> findLatest(int limit);

    List<Product> findByPriceRange(BigDecimal min, BigDecimal max);

    List<Product> findByCategoryAndActive(int categoryId);

    List<Product> searchAndFilter(String keyword,
                                  Integer categoryId,
                                  BigDecimal minPrice,
                                  BigDecimal maxPrice,
                                  Boolean isActive,
                                  String sortBy);

    int countProducts();
    
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
}
