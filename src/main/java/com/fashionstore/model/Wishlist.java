package com.fashionstore.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Wishlist {

    private int wishlistId;

    private int userId;

    private int productId;

    private Timestamp createdAt;

    private String productName;

    private String imageUrl;

    private String categoryName;

    private BigDecimal price;
  
    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

	public java.math.BigDecimal getPrice() {
        return price;
    }

    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
    }
    
    public Wishlist(int wishlistId, int userId, int productId, Timestamp createdAt, String productName, String imageUrl,
 			String categoryName, BigDecimal price) {
 		super();
 		this.wishlistId = wishlistId;
 		this.userId = userId;
 		this.productId = productId;
 		this.createdAt = createdAt;
 		this.productName = productName;
 		this.imageUrl = imageUrl;
 		this.categoryName = categoryName;
 		this.price = price;
 	}

	public Wishlist() {
		
	}

    
}