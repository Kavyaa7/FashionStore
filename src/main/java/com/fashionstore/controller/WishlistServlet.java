package com.fashionstore.controller;

import com.fashionstore.model.Wishlist;
import com.fashionstore.service.WishlistService;
import com.fashionstore.service.impl.WishlistServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/wishlist")
public class WishlistServlet
        extends HttpServlet {

    private WishlistService wishlistService;

    @Override
    public void init() {

        wishlistService =
                new WishlistServiceImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {


        int userId = 1;

        List<Wishlist> wishlistItems =
                wishlistService.findByUserId(
                        userId
                );

        request.setAttribute(
                "wishlistItems",
                wishlistItems
        );

        request.getRequestDispatcher(
                "/views/product/wishlist.jsp"
        ).forward(request, response);
    }
}