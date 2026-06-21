package com.fashionstore.dao;

import com.fashionstore.model.Wishlist;

import java.util.List;

public interface WishlistDAO {

    boolean exists(int userId, int productId);

    int save(Wishlist wishlist);

    List<Wishlist> findByUserId(int userId);

    boolean remove(int userId, int productId);
}