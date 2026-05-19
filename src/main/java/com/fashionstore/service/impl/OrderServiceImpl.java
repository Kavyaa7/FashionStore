package com.fashionstore.service.impl;

import java.util.List;

import com.fashionstore.dao.OrderDAO;
import com.fashionstore.dao.impl.OrderDAOImpl;
import com.fashionstore.model.Order;
import com.fashionstore.service.OrderService;

public class OrderServiceImpl
        implements OrderService {

    private final OrderDAO orderDAO;

    public OrderServiceImpl() {

        orderDAO = new OrderDAOImpl();
    }

    @Override
    public int save(Order order) {

        return orderDAO.save(order);
    }
    
    @Override
    public List<Order> findByUserId(int userId) {

        return orderDAO.findByUserId(userId);
    }
}