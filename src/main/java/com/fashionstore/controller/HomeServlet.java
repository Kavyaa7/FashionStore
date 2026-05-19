package com.fashionstore.controller;

import com.fashionstore.model.Category;
import com.fashionstore.model.Product;
import com.fashionstore.service.CategoryService;
import com.fashionstore.service.ProductService;
import com.fashionstore.service.impl.CategoryServiceImpl;
import com.fashionstore.service.impl.ProductServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private ProductService productService;
    private CategoryService categoryService;

    @Override
    public void init() {

        productService = new ProductServiceImpl();
        categoryService = new CategoryServiceImpl();

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String categoryIdParam =
    	        request.getParameter("categoryId");

    	String priceRange =
    	        request.getParameter("priceRange");
    	
    	String keyword =
    	        request.getParameter("keyword");

    	String sortBy =
    	        request.getParameter("sortBy");


    	List<Product> products =
    	        productService.searchFilterSortProducts(
    	                categoryIdParam,
    	                priceRange,
    	                keyword,
    	                sortBy
    	        );
    	
        List<Category> categories = categoryService.getAllCategories();

        request.setAttribute("products", products);
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/views/product/home.jsp")
                .forward(request, response);

    }
}