package com.fashionstore.service;

import java.util.List;

import com.fashionstore.model.CartItem;

public interface CartItemService {

    CartItem findByCartAndProductSize(
            int cartId,
            int productSizeId
    );

    int save(CartItem item);

    boolean update(CartItem item);
    
    List<CartItem> findByCartId(int cartId);
    
    boolean delete(int cartItemId);
    
    CartItem findById(int cartItemId);
}