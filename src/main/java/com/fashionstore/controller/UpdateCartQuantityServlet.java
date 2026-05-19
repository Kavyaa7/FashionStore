package com.fashionstore.controller;

import com.fashionstore.model.CartItem;
import com.fashionstore.service.CartItemService;
import com.fashionstore.service.impl.CartItemServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/update-cart-quantity")
public class UpdateCartQuantityServlet
        extends HttpServlet {

    private CartItemService cartItemService;

    @Override
    public void init() {

        cartItemService =
                new CartItemServiceImpl();
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

            String action =
                    request.getParameter("action");

            CartItem item =
                    cartItemService.findById(cartItemId);

            if (item != null) {

                int quantity = item.getQuantity();

                if ("increase".equals(action)) {

                    quantity++;

                } else if ("decrease".equals(action)) {

                    quantity--;
                }

                /*
                 * REMOVE IF QUANTITY <= 0
                 */

                if (quantity <= 0) {

                    cartItemService.delete(cartItemId);

                } else {

                    item.setQuantity(quantity);

                    cartItemService.update(item);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        response.sendRedirect(
                request.getContextPath() + "/cart"
        );
    }
}