package com.fashionstore.service.impl;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.dao.impl.CartDAOImpl;
import com.fashionstore.model.Cart;
import com.fashionstore.service.CartService;

public class CartServiceImpl implements CartService {

    private final CartDAO cartDAO;

    public CartServiceImpl() {

        cartDAO = new CartDAOImpl();
    }

    @Override
    public Cart getCartByUserId(int userId) {

        return cartDAO.findByUserId(userId);
    }

    @Override
    public Cart getCartById(int cartId) {

        return cartDAO.findById(cartId);
    }

    @Override
    public int createCart(int userId) {

        Cart cart = new Cart();

        cart.setUserId(userId);

        return cartDAO.save(cart);
    }
}