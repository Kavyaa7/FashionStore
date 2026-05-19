package com.fashionstore.service.impl;

import java.util.List;

import com.fashionstore.dao.OrderItemDAO;
import com.fashionstore.dao.impl.OrderItemDAOImpl;
import com.fashionstore.model.OrderItem;
import com.fashionstore.service.OrderItemService;

public class OrderItemServiceImpl
        implements OrderItemService {

    private final OrderItemDAO orderItemDAO;

    public OrderItemServiceImpl() {

        orderItemDAO =
                new OrderItemDAOImpl();
    }

    @Override
    public int save(OrderItem orderItem) {

        return orderItemDAO.save(orderItem);
    }
    
    @Override
    public List<OrderItem> findByOrderId(int orderId) {

        return orderItemDAO.findByOrderId(orderId);
    }
}