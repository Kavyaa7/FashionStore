package com.fashionstore.dao.impl;

import com.fashionstore.dao.OrderDAO;
import com.fashionstore.model.Order;
import com.fashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public int save(Order order) {
        String sql = "INSERT INTO orders (user_id, total_amount, payment_method, order_status, delivery_address, payment_status) VALUES (?, ?, ?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUserId());
            ps.setBigDecimal(2, order.getTotalAmount());
            ps.setString(3, order.getPaymentMethod());
            ps.setString(4, order.getOrderStatus());
            ps.setString(5, order.getDeliveryAddress());
            ps.setString(6, order.getPaymentStatus());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    @Override
    public Order findById(int orderId) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        Order order = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = mapRow(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<Order> findByUserId(int userId) {

        List<Order> orders = new ArrayList<>();

        String sql = """
                SELECT *
                FROM orders
                WHERE user_id = ?
                ORDER BY order_id DESC
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Order order = new Order();

                order.setOrderId(
                        rs.getInt("order_id")
                );

                order.setUserId(
                        rs.getInt("user_id")
                );

                order.setTotalAmount(
                        rs.getBigDecimal("total_amount")
                );

                order.setDeliveryAddress(
                        rs.getString("delivery_address")
                );

                order.setOrderStatus(
                        rs.getString("order_status")
                );

                order.setPaymentStatus(
                        rs.getString("payment_status")
                );

                order.setOrderDate(
                        rs.getTimestamp("order_date")
                );

                orders.add(order);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT * FROM orders";
        List<Order> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean update(Order order) {
        String sql = "UPDATE orders SET user_id=?, total_amount=?, payment_method=?, order_status=?, delivery_address=?, payment_status=? WHERE order_id=?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, order.getUserId());
            ps.setBigDecimal(2, order.getTotalAmount());
            ps.setString(3, order.getPaymentMethod());
            ps.setString(4, order.getOrderStatus());
            ps.setString(5, order.getDeliveryAddress());
            ps.setString(6, order.getPaymentStatus());
            ps.setInt(7, order.getOrderId());

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public boolean updateStatus(int orderId, String orderStatus) {
        String sql = "UPDATE orders SET order_status=? WHERE order_id=?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, orderStatus);
            ps.setInt(2, orderId);

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public boolean updatePaymentStatus(int orderId, String paymentStatus) {
        String sql = "UPDATE orders SET payment_status=? WHERE order_id=?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, paymentStatus);
            ps.setInt(2, orderId);

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public List<Order> findByStatus(String status) {
        String sql = "SELECT * FROM orders WHERE order_status = ?";
        List<Order> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public int countUserOrders(int userId) {
        String sql = "SELECT COUNT(*) FROM orders WHERE user_id=?";
        int count = 0;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public boolean delete(int orderId) {
        String sql = "DELETE FROM orders WHERE order_id=?";
        boolean deleted = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            deleted = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }

    private Order mapRow(ResultSet rs) throws SQLException {
        Order order = new Order();

        order.setOrderId(rs.getInt("order_id"));
        order.setUserId(rs.getInt("user_id"));
        order.setOrderDate(rs.getTimestamp("order_date"));
        order.setTotalAmount(rs.getBigDecimal("total_amount"));
        order.setPaymentMethod(rs.getString("payment_method"));
        order.setOrderStatus(rs.getString("order_status"));
        order.setDeliveryAddress(rs.getString("delivery_address"));
        order.setPaymentStatus(rs.getString("payment_status"));

        return order;
    }
}