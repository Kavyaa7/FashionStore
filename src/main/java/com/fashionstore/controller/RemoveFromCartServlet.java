package com.fashionstore.controller;

import com.fashionstore.service.CartItemService;
import com.fashionstore.service.impl.CartItemServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {

    private CartItemService cartItemService;

    @Override
    public void init() {

        cartItemService = new CartItemServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int cartItemId =
                    Integer.parseInt(
                            request.getParameter("cartItemId")
                    );

            cartItemService.delete(cartItemId);

        } catch (Exception e) {

            e.printStackTrace();
        }

        response.sendRedirect(
                request.getContextPath() + "/cart"
        );
    }
}