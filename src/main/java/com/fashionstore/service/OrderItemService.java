package com.fashionstore.service;

import java.util.List;

import com.fashionstore.model.OrderItem;

public interface OrderItemService {

    int save(OrderItem orderItem);
    
    List<OrderItem> findByOrderId(int orderId);

}