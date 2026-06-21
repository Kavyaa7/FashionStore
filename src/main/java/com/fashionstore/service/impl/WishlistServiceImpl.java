package com.fashionstore.service.impl;

import com.fashionstore.dao.WishlistDAO;
import com.fashionstore.dao.impl.WishlistDAOImpl;
import com.fashionstore.model.Wishlist;
import com.fashionstore.service.WishlistService;

import java.util.List;

public class WishlistServiceImpl
        implements WishlistService {

    private final WishlistDAO wishlistDAO;

    public WishlistServiceImpl() {

        wishlistDAO = new WishlistDAOImpl();
    }

    @Override
    public boolean exists(
            int userId,
            int productId
    ) {

        return wishlistDAO.exists(
                userId,
                productId
        );
    }

    @Override
    public int save(Wishlist wishlist) {

        return wishlistDAO.save(wishlist);
    }

    @Override
    public List<Wishlist> findByUserId(int userId) {

        return wishlistDAO.findByUserId(userId);
    }

    @Override
    public boolean remove(
            int userId,
            int productId
    ) {

        return wishlistDAO.remove(
                userId,
                productId
        );
    }
}