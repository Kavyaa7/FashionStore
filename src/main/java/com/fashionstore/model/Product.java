package com.fashionstore.model;

import java.math.BigDecimal;

public class Product {

    private int productId;
    private int categoryId;
    private String productName;
    private String description;
    private BigDecimal discountPercent;
    private String imageUrl;
    private boolean isActive;
    private BigDecimal price;
    private Category category;
    private int stockQuantity;

    public Product() {
    }

    public Product(int productId, int categoryId, String productName, String description,
                   BigDecimal discountPercent, String imageUrl, boolean isActive, BigDecimal price) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.description = description;
        this.discountPercent = discountPercent;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
        this.price = price;
    }

    public Product(int categoryId, String productName, String description,
                   BigDecimal discountPercent, String imageUrl, boolean isActive, BigDecimal price) {
        this.categoryId = categoryId;
        this.productName = productName;
        this.description = description;
        this.discountPercent = discountPercent;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	@Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", categoryId=" + categoryId +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", discountPercent=" + discountPercent +
                ", imageUrl='" + imageUrl + '\'' +
                ", isActive=" + isActive +
                ", price=" + price +
                '}';
    }
}