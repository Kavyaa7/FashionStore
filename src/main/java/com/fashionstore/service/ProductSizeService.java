package com.fashionstore.service;

import com.fashionstore.model.ProductSize;

import java.util.List;

public interface ProductSizeService {

    List<ProductSize> findByProductId(int productId);

}