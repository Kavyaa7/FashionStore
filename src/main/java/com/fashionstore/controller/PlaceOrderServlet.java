package com.fashionstore.controller;

import com.fashionstore.model.*;
import com.fashionstore.service.*;
import com.fashionstore.service.impl.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/place-order")
public class PlaceOrderServlet extends HttpServlet {

    private CartService cartService;
    private CartItemService cartItemService;

    private OrderService orderService;
    private OrderItemService orderItemService;
    
    private ProductService productService;

    @Override
    public void init() {

        cartService = new CartServiceImpl();

        cartItemService =
                new CartItemServiceImpl();

        orderService =
                new OrderServiceImpl();

        orderItemService =
                new OrderItemServiceImpl();
        
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            HttpSession session =
                    request.getSession(false);

            User loggedInUser =
                    (User) session.getAttribute(
                            "loggedInUser"
                    );

            /*
             * GET CART
             */

            Cart cart =
                    cartService.getCartByUserId(
                            loggedInUser.getUserId()
                    );

            if (cart == null) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/cart"
                );

                return;
            }

            /*
             * GET CART ITEMS
             */

            List<CartItem> cartItems =
                    cartItemService.findByCartId(
                            cart.getCartId()
                    );

            if (cartItems.isEmpty()) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/cart"
                );

                return;
            }

            /*
             * CALCULATE TOTAL
             */

            BigDecimal totalAmount =
                    BigDecimal.ZERO;

            for (CartItem item : cartItems) {

                BigDecimal subtotal =
                        item.getPrice().multiply(
                                BigDecimal.valueOf(
                                        item.getQuantity()
                                )
                        );

                totalAmount =
                        totalAmount.add(subtotal);
            }

            /*
             * CREATE ORDER
             */

            Order order = new Order();

            order.setUserId(
                    loggedInUser.getUserId()
            );

            order.setTotalAmount(totalAmount);

            order.setDeliveryAddress(
                    request.getParameter(
                            "deliveryAddress"
                    )
            );

            order.setOrderStatus("PLACED");

            order.setPaymentStatus("PENDING");

            int orderId =
                    orderService.save(order);

            /*
             * CREATE ORDER ITEMS
             */

            for (CartItem cartItem : cartItems) {

                OrderItem orderItem =
                        new OrderItem();

                orderItem.setOrderId(orderId);

                orderItem.setProductId(
                        cartItem.getProductId()
                );

                orderItem.setProductSizeId(
                        cartItem.getProductSizeId()
                );

                orderItem.setQuantity(
                        cartItem.getQuantity()
                );

                orderItem.setUnitPrice(
                        cartItem.getPrice()
                );

                orderItemService.save(orderItem);
            }
            
            for (CartItem item : cartItems) {

                productService.reduceStock(
                        item.getProductId(),
                        item.getQuantity()
                );
            }

            /*
             * CLEAR CART
             */

            for (CartItem item : cartItems) {

                cartItemService.delete(
                        item.getCartItemId()
                );
            }

            /*
             * REDIRECT SUCCESS
             */

            response.sendRedirect(
                    request.getContextPath()
                            + "/views/order/order-success.jsp"
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                            + "/checkout"
            );
        }
    }
}