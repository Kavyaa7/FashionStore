package com.fashionstore.controller;

import com.fashionstore.model.Cart;
import com.fashionstore.model.CartItem;
import com.fashionstore.model.User;
import com.fashionstore.service.CartItemService;
import com.fashionstore.service.CartService;
import com.fashionstore.service.impl.CartItemServiceImpl;
import com.fashionstore.service.impl.CartServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    private CartService cartService;
    private CartItemService cartItemService;

    @Override
    public void init() {

        cartService = new CartServiceImpl();
        cartItemService = new CartItemServiceImpl();

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

      
        User loggedInUser =
                (User) session.getAttribute(
                        "loggedInUser"
                );

        if (loggedInUser == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/views/user/login.jsp"
            );

            return;
        }
        
        String action =
                request.getParameter("action");

        int userId = loggedInUser.getUserId();

        try {

            int productId =
                    Integer.parseInt(request.getParameter("productId"));

            int productSizeId =
                    Integer.parseInt(request.getParameter("productSizeId"));

            int quantity =
                    Integer.parseInt(request.getParameter("quantity"));

            /*
             * GET OR CREATE CART
             */

            Cart cart = cartService.getCartByUserId(userId);

            if (cart == null) {

                int cartId = cartService.createCart(userId);

                cart = cartService.getCartById(cartId);
            }

            /*
             * CHECK IF PRODUCT ALREADY EXISTS
             */

            CartItem existingItem =
                    cartItemService.findByCartAndProductSize(
                            cart.getCartId(),
                            productSizeId
                    );

            if (existingItem != null) {

                int newQuantity =
                        existingItem.getQuantity() + quantity;

                existingItem.setQuantity(newQuantity);

                cartItemService.update(existingItem);

            } else {

                CartItem item = new CartItem();

                item.setCartId(cart.getCartId());
                item.setProductId(productId);
                item.setProductSizeId(productSizeId);
                item.setQuantity(quantity);

                cartItemService.save(item);
            }

            if("buyNow".equals(action)) {

                response.sendRedirect(
                        request.getContextPath() + "/cart"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/product-details?id="
                        + productId
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath() + "/home"
            );
        }
    }
}