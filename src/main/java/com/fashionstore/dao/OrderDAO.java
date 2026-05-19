package com.fashionstore.dao;

import com.fashionstore.model.Order;
import java.util.List;

public interface OrderDAO {

    int save(Order order);

    Order findById(int orderId);

    List<Order> findByUserId(int userId);

    List<Order> findAll();

    boolean update(Order order);

    boolean updateStatus(int orderId, String orderStatus);

    boolean delete(int orderId);
    
    List<Order> findByStatus(String status);

    boolean updatePaymentStatus(int orderId, String paymentStatus);

    int countUserOrders(int userId);
}
