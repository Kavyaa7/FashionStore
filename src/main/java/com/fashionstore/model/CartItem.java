package com.fashionstore.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CartItem {

    private int cartItemId;
    private int cartId;
    private int productId;
    private int quantity;
    private BigDecimal unitPrice;
    private Timestamp addedAt;
    private int productSizeId;
    private String productName;
    private String imageUrl;
    private BigDecimal price;
    private String sizeLabel;

    public CartItem() {
    }

    public CartItem(int cartItemId, int cartId, int productId, int quantity,
                    BigDecimal unitPrice, Timestamp addedAt, int productSizeId) {
        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.addedAt = addedAt;
        this.productSizeId = productSizeId;
    }

    public CartItem(int cartId, int productId, int quantity,
                    BigDecimal unitPrice, Timestamp addedAt, int productSizeId) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.addedAt = addedAt;
        this.productSizeId = productSizeId;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }

    public int getProductSizeId() {
        return productSizeId;
    }

    public void setProductSizeId(int productSizeId) {
        this.productSizeId = productSizeId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSizeLabel() {
        return sizeLabel;
    }

    public void setSizeLabel(String sizeLabel) {
        this.sizeLabel = sizeLabel;
    }
    
    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                ", cartId=" + cartId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", addedAt=" + addedAt +
                ", productSizeId=" + productSizeId +
                '}';
    }
}