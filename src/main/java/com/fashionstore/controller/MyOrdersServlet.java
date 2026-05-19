package com.fashionstore.controller;

import com.fashionstore.model.Order;
import com.fashionstore.model.OrderItem;
import com.fashionstore.model.User;
import com.fashionstore.service.OrderItemService;
import com.fashionstore.service.OrderService;
import com.fashionstore.service.impl.OrderItemServiceImpl;
import com.fashionstore.service.impl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/my-orders")
public class MyOrdersServlet
        extends HttpServlet {

    private OrderService orderService;

    private OrderItemService orderItemService;

    @Override
    public void init() {

        orderService =
                new OrderServiceImpl();

        orderItemService =
                new OrderItemServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        User loggedInUser =
                (User) session.getAttribute(
                        "loggedInUser"
                );

        List<Order> orders =
                orderService.findByUserId(
                        loggedInUser.getUserId()
                );

        for (Order order : orders) {

            List<OrderItem> items =
                    orderItemService.findByOrderId(
                            order.getOrderId()
                    );

            order.setOrderItems(items);
        }

        request.setAttribute("orders", orders);

        request.getRequestDispatcher(
                "/views/order/my-orders.jsp"
        ).forward(request, response);
    }
}