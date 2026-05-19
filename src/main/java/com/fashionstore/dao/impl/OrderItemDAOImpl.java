package com.fashionstore.dao.impl;

import com.fashionstore.dao.OrderItemDAO;
import com.fashionstore.model.OrderItem;
import com.fashionstore.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {

    @Override
    public int save(OrderItem item) {
        String sql = "INSERT INTO order_items (order_id, product_id, product_name, quantity, unit_price, subtotal, size_label, product_size_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getProductId());
            ps.setString(3, item.getProductName());
            ps.setInt(4, item.getQuantity());
            ps.setBigDecimal(5, item.getUnitPrice());
            ps.setBigDecimal(6, item.getSubtotal());
            ps.setString(7, item.getSizeLabel());
            ps.setInt(8, item.getProductSizeId());

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
    public OrderItem findById(int orderItemId) {
        String sql = "SELECT * FROM order_items WHERE order_item_id = ?";
        OrderItem item = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderItemId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item = mapRow(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public List<OrderItem> findByOrderId(int orderId) {

        List<OrderItem> list = new ArrayList<>();

        String sql = """
                SELECT oi.*,
                       p.product_name,
                       p.image_url,
                       ps.size_label
                FROM order_items oi
                JOIN products p
                     ON oi.product_id = p.product_id
                JOIN product_sizes ps
                     ON oi.product_size_id =
                        ps.product_size_id
                WHERE oi.order_id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                OrderItem item =
                        new OrderItem();

                item.setOrderItemId(
                        rs.getInt("order_item_id")
                );

                item.setOrderId(
                        rs.getInt("order_id")
                );

                item.setProductId(
                        rs.getInt("product_id")
                );

                item.setProductSizeId(
                        rs.getInt("product_size_id")
                );

                item.setQuantity(
                        rs.getInt("quantity")
                );

                item.setUnitPrice(
                        rs.getBigDecimal("price")
                );

                item.setProductName(
                        rs.getString("product_name")
                );

                item.setImageUrl(
                        rs.getString("image_url")
                );

                item.setSizeLabel(
                        rs.getString("size_label")
                );

                list.add(item);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean update(OrderItem item) {
        String sql = "UPDATE order_items SET order_id=?, product_id=?, product_name=?, quantity=?, unit_price=?, subtotal=?, size_label=?, product_size_id=? WHERE order_item_id=?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getProductId());
            ps.setString(3, item.getProductName());
            ps.setInt(4, item.getQuantity());
            ps.setBigDecimal(5, item.getUnitPrice());
            ps.setBigDecimal(6, item.getSubtotal());
            ps.setString(7, item.getSizeLabel());
            ps.setInt(8, item.getProductSizeId());
            ps.setInt(9, item.getOrderItemId());

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public boolean delete(int orderItemId) {
        String sql = "DELETE FROM order_items WHERE order_item_id = ?";
        boolean deleted = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderItemId);
            deleted = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }

    @Override
    public boolean deleteByOrderId(int orderId) {
        String sql = "DELETE FROM order_items WHERE order_id = ?";
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

    @Override
    public double getOrderTotal(int orderId) {
        String sql = "SELECT SUM(subtotal) AS total FROM order_items WHERE order_id = ?";
        double total = 0;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getDouble("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    private OrderItem mapRow(ResultSet rs) throws SQLException {
        OrderItem item = new OrderItem();

        item.setOrderItemId(rs.getInt("order_item_id"));
        item.setOrderId(rs.getInt("order_id"));
        item.setProductId(rs.getInt("product_id"));
        item.setProductName(rs.getString("product_name"));
        item.setQuantity(rs.getInt("quantity"));
        item.setUnitPrice(rs.getBigDecimal("unit_price"));
        item.setSubtotal(rs.getBigDecimal("subtotal"));
        item.setSizeLabel(rs.getString("size_label"));
        item.setProductSizeId(rs.getInt("product_size_id"));

        return item;
    }
}