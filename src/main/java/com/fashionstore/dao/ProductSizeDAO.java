package com.fashionstore.dao;

import com.fashionstore.model.ProductSize;
import java.util.List;

public interface ProductSizeDAO {

    int save(ProductSize productSize);

    ProductSize findById(int productSizeId);

    List<ProductSize> findByProductId(int productId);

    List<ProductSize> findAvailableByProductId(int productId);

    boolean update(ProductSize productSize);

    boolean delete(int productSizeId);
    
    ProductSize findByProductAndSize(int productId, String sizeLabel);

    boolean updateStock(int productSizeId, int quantity);

    boolean reduceStock(int productSizeId, int quantity);
}
