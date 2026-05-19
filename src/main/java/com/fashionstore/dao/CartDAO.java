package com.fashionstore.dao;

import com.fashionstore.model.Cart;
import java.util.List;

public interface CartDAO {

    int save(Cart cart);

    Cart findById(int cartId);

    Cart findByUserId(int userId);

    List<Cart> findAll();

    boolean update(Cart cart);

    boolean delete(int cartId);
    
    boolean existsByUserId(int userId);

    int createCartIfNotExists(int userId);
}