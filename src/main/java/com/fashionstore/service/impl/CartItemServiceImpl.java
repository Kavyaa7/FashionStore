package com.fashionstore.service.impl;

import java.util.List;

import com.fashionstore.dao.CartItemDAO;
import com.fashionstore.dao.impl.CartItemDAOImpl;
import com.fashionstore.model.CartItem;
import com.fashionstore.service.CartItemService;

public class CartItemServiceImpl
        implements CartItemService {

    private final CartItemDAO cartItemDAO;

    public CartItemServiceImpl() {

        cartItemDAO = new CartItemDAOImpl();
    }

    @Override
    public CartItem findByCartAndProductSize(
            int cartId,
            int productSizeId
    ) {

        return cartItemDAO.findByCartAndProductSize(
                cartId,
                productSizeId
        );
    }
    
    @Override
    public CartItem findById(int cartItemId) {

        return cartItemDAO.findById(cartItemId);
    }

    @Override
    public int save(CartItem item) {

        return cartItemDAO.save(item);
    }

    @Override
    public boolean update(CartItem item) {

        return cartItemDAO.update(item);
    }
    
    @Override
    public List<CartItem> findByCartId(int cartId) {

        return cartItemDAO.findByCartId(cartId);
    }
    
    @Override
    public boolean delete(int cartItemId) {

        return cartItemDAO.delete(cartItemId);
    }
}