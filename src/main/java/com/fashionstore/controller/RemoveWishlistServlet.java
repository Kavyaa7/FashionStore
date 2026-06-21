package com.fashionstore.controller;

import com.fashionstore.service.WishlistService;
import com.fashionstore.service.impl.WishlistServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/remove-wishlist")
public class RemoveWishlistServlet
        extends HttpServlet {

    private WishlistService wishlistService;

    @Override
    public void init() {

        wishlistService =
                new WishlistServiceImpl();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        int userId = 1;

        try {

            int productId =
                    Integer.parseInt(
                            request.getParameter(
                                    "productId"
                            )
                    );

            wishlistService.remove(
                    userId,
                    productId
            );

        } catch(Exception e) {

            e.printStackTrace();
        }

        response.sendRedirect(
                request.getContextPath()
                + "/wishlist"
        );
    }
}