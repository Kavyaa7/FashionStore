package com.fashionstore.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Order {

    private int orderId;
    private int userId;
    private Timestamp orderDate;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private String orderStatus;
    private String deliveryAddress;
    private String paymentStatus;
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(int orderId, int userId, Timestamp orderDate, BigDecimal totalAmount,
                 String paymentMethod, String orderStatus, String deliveryAddress, String paymentStatus) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.paymentStatus = paymentStatus;
    }

    public Order(int userId, Timestamp orderDate, BigDecimal totalAmount,
                 String paymentMethod, String orderStatus, String deliveryAddress, String paymentStatus) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.paymentStatus = paymentStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<OrderItem> getOrderItems() {
		return orderItems;
	}
    public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
    
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}