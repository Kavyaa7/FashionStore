package com.fashionstore.util;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.model.Product;

import java.util.List;

public class TestProductDAO {

    public static void main(String[] args) {

        ProductDAO productDAO = new ProductDAOImpl();

        // 🔹 Test findAll()
        List<Product> products = productDAO.findAll();

        System.out.println("===== PRODUCT LIST =====");

        for (Product product : products) {
            System.out.println(product);
        }

        // 🔹 Test findById()
        System.out.println("\n===== PRODUCT BY ID =====");

        Product p = productDAO.findById(1);

        if (p != null) {
            System.out.println(p);
        } else {
            System.out.println("Product not found");
        }

        // 🔹 Test searchByName()
        System.out.println("\n===== SEARCH RESULT =====");

        List<Product> searchResults = productDAO.searchByName("shirt");

        for (Product product : searchResults) {
            System.out.println(product);
        }

        // 🔹 Test filterProducts()
        System.out.println("\n===== FILTER RESULT =====");

        List<Product> filtered = productDAO.filterProducts(
                null,
                null,
                null,
                true
        );

        for (Product product : filtered) {
            System.out.println(product);
        }

        // 🔹 Test latest products
        System.out.println("\n===== LATEST PRODUCTS =====");

        List<Product> latest = productDAO.findLatest(5);

        for (Product product : latest) {
            System.out.println(product);
        }
    }
}