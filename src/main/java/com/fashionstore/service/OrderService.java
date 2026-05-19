package com.fashionstore.service;

import java.util.List;

import com.fashionstore.model.Order;

public interface OrderService {

    int save(Order order);

    List<Order> findByUserId(int userId);
}