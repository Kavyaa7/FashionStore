package com.fashionstore.service;

import com.fashionstore.model.Wishlist;

import java.util.List;

public interface WishlistService {

    boolean exists(int userId, int productId);

    int save(Wishlist wishlist);

    List<Wishlist> findByUserId(int userId);

    boolean remove(int userId, int productId);
}