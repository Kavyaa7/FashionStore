package com.fashionstore.controller;

import com.fashionstore.model.Product;
import com.fashionstore.model.ProductSize;
import com.fashionstore.service.ProductService;
import com.fashionstore.service.ProductSizeService;
import com.fashionstore.service.impl.ProductServiceImpl;
import com.fashionstore.service.impl.ProductSizeServiceImpl;
import com.fashionstore.service.impl.WishlistServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/product-details")
public class ProductDetailsServlet extends HttpServlet {

    private ProductService productService;
    private ProductSizeService productSizeService;
    private WishlistServiceImpl wishlistService;

    @Override
    public void init() {

        productService = new ProductServiceImpl();
        productSizeService = new ProductSizeServiceImpl();
        wishlistService = new WishlistServiceImpl();

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String productIdParam = request.getParameter("id");

        if (productIdParam == null || productIdParam.isEmpty()) {

            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        try {

            int productId = Integer.parseInt(productIdParam);

            Product product = productService.findById(productId);

            if (product == null) {

                response.sendRedirect(request.getContextPath() + "/");
                return;
            }

            List<ProductSize> sizes =
                    productSizeService.findByProductId(productId);

            request.setAttribute("product", product);
            request.setAttribute("sizes", sizes);
            
            int userId = 1;

            boolean inWishlist =
                    wishlistService.exists(
                            userId,
                            productId
                    );

            request.setAttribute(
                    "inWishlist",
                    inWishlist
            );

            request.getRequestDispatcher(
                    "/views/product/product-details.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {

            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}