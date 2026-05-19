package com.fashionstore.dao.impl;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.model.Cart;
import com.fashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {

    @Override
    public int save(Cart cart) {
        String sql = "INSERT INTO cart (user_id) VALUES (?)";
        int generatedId = -1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, cart.getUserId());
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
    public Cart findById(int cartId) {
        String sql = "SELECT * FROM cart WHERE cart_id = ?";
        Cart cart = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cart = mapRow(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cart;
    }

    @Override
    public Cart findByUserId(int userId) {
        String sql = "SELECT * FROM cart WHERE user_id = ?";
        Cart cart = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cart = mapRow(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cart;
    }

    @Override
    public List<Cart> findAll() {
        String sql = "SELECT * FROM cart";
        List<Cart> list = new ArrayList<>();

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
    public boolean update(Cart cart) {
        String sql = "UPDATE cart SET user_id=? WHERE cart_id=?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cart.getUserId());
            ps.setInt(2, cart.getCartId());

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public boolean delete(int cartId) {
        String sql = "DELETE FROM cart WHERE cart_id = ?";
        boolean deleted = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartId);
            deleted = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }

    @Override
    public boolean existsByUserId(int userId) {
        String sql = "SELECT 1 FROM cart WHERE user_id = ?";
        boolean exists = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                exists = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    @Override
    public int createCartIfNotExists(int userId) {
        Cart existing = findByUserId(userId);
        if (existing != null) {
            return existing.getCartId();
        }

        String sql = "INSERT INTO cart (user_id) VALUES (?)";
        int generatedId = -1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, userId);
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

    private Cart mapRow(ResultSet rs) throws SQLException {
        Cart cart = new Cart();

        cart.setCartId(rs.getInt("cart_id"));
        cart.setUserId(rs.getInt("user_id"));
        cart.setCreatedAt(rs.getTimestamp("created_at"));
        cart.setUpdatedAt(rs.getTimestamp("updated_at"));

        return cart;
    }
}