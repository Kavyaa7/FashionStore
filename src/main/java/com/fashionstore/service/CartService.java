package com.fashionstore.service;

import com.fashionstore.model.Cart;

public interface CartService {

    Cart getCartByUserId(int userId);

    Cart getCartById(int cartId);

    int createCart(int userId);

}