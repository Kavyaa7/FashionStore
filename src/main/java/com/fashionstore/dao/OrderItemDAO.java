package com.fashionstore.dao;

import com.fashionstore.model.OrderItem;
import java.util.List;

public interface OrderItemDAO {

    int save(OrderItem orderItem);

    OrderItem findById(int orderItemId);

    List<OrderItem> findByOrderId(int orderId);

    boolean update(OrderItem orderItem);

    boolean delete(int orderItemId);

    boolean deleteByOrderId(int orderId);
    
    double getOrderTotal(int orderId);
}