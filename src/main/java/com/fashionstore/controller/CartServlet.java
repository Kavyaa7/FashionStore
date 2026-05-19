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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private CartService cartService;
    private CartItemService cartItemService;

    @Override
    public void init() {

        cartService = new CartServiceImpl();
        cartItemService = new CartItemServiceImpl();

    }

    @Override
    protected void doGet(HttpServletRequest request,
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

    	int userId = loggedInUser.getUserId();

        Cart cart = cartService.getCartByUserId(userId);

        List<CartItem> cartItems = new ArrayList<>();

        BigDecimal totalAmount = BigDecimal.ZERO;

        if (cart != null) {

            cartItems =
                    cartItemService.findByCartId(
                            cart.getCartId()
                    );

            for (CartItem item : cartItems) {

                BigDecimal subtotal =
                        item.getPrice()
                                .multiply(
                                        BigDecimal.valueOf(
                                                item.getQuantity()
                                        )
                                );

                totalAmount = totalAmount.add(subtotal);
            }
        }

        request.setAttribute("cartItems", cartItems);
        request.setAttribute("totalAmount", totalAmount);

        request.getRequestDispatcher(
                "/views/cart/cart.jsp"
        ).forward(request, response);
    }
}