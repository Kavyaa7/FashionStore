package com.fashionstore.dao;

import com.fashionstore.model.CartItem;
import java.util.List;

public interface CartItemDAO {

    int save(CartItem cartItem);

    CartItem findById(int cartItemId);

    List<CartItem> findByCartId(int cartId);

    CartItem findByCartAndProductAndSize(int cartId, int productId, int productSizeId);

    boolean update(CartItem cartItem);

    boolean delete(int cartItemId);

    boolean deleteByCartId(int cartId);
    
    boolean updateQuantity(int cartItemId, int quantity);

    boolean incrementQuantity(int cartId, int productId, int productSizeId);

    boolean decrementQuantity(int cartItemId);

    double getCartTotal(int cartId);

    int getCartItemCount(int cartId);
    
    CartItem findByCartAndProductSize(
            int cartId,
            int productSizeId
    );
    
}
