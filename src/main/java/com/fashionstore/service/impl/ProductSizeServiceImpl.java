package com.fashionstore.service.impl;

import com.fashionstore.dao.ProductSizeDAO;
import com.fashionstore.dao.impl.ProductSizeDAOImpl;
import com.fashionstore.model.ProductSize;
import com.fashionstore.service.ProductSizeService;

import java.util.List;

public class ProductSizeServiceImpl implements ProductSizeService {

    private final ProductSizeDAO productSizeDAO;

    public ProductSizeServiceImpl() {

        productSizeDAO = new ProductSizeDAOImpl();
    }

    @Override
    public List<ProductSize> findByProductId(int productId) {

        return productSizeDAO.findByProductId(productId);
    }
}