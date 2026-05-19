package com.fashionstore.dao.impl;

import com.fashionstore.dao.CartItemDAO;
import com.fashionstore.model.CartItem;
import com.fashionstore.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAOImpl implements CartItemDAO {

    @Override
    public int save(CartItem item) {
        String sql = "INSERT INTO cart_items (cart_id, product_id, quantity, unit_price, product_size_id) VALUES (?, ?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, item.getCartId());
            ps.setInt(2, item.getProductId());
            ps.setInt(3, item.getQuantity());
            ps.setBigDecimal(4, item.getUnitPrice());
            ps.setInt(5, item.getProductSizeId());

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
    public CartItem findById(int cartItemId) {

        String sql = """
                SELECT *
                FROM cart_items
                WHERE cart_item_id = ?
                """;

        CartItem item = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, cartItemId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                item = new CartItem();

                item.setCartItemId(
                        rs.getInt("cart_item_id")
                );

                item.setCartId(
                        rs.getInt("cart_id")
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
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return item;
    }

    @Override
    public List<CartItem> findByCartId(int cartId) {

        List<CartItem> list = new ArrayList<>();

        String sql = """
                SELECT ci.*,
                       p.product_name,
                       p.image_url,
                       p.price,
                       ps.size_label
                FROM cart_items ci
                JOIN products p
                     ON ci.product_id = p.product_id
                JOIN product_sizes ps
                     ON ci.product_size_id = ps.product_size_id
                WHERE ci.cart_id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, cartId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CartItem item = new CartItem();

                item.setCartItemId(
                        rs.getInt("cart_item_id")
                );

                item.setCartId(
                        rs.getInt("cart_id")
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

                item.setProductName(
                        rs.getString("product_name")
                );

                item.setImageUrl(
                        rs.getString("image_url")
                );

                item.setPrice(
                        rs.getBigDecimal("price")
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
    public CartItem findByCartAndProductAndSize(int cartId, int productId, int productSizeId) {
        String sql = "SELECT * FROM cart_items WHERE cart_id=? AND product_id=? AND product_size_id=?";
        CartItem item = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            ps.setInt(3, productSizeId);

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
    public boolean update(CartItem item) {
        String sql = "UPDATE cart_items SET cart_id=?, product_id=?, quantity=?, unit_price=?, product_size_id=? WHERE cart_item_id=?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, item.getCartId());
            ps.setInt(2, item.getProductId());
            ps.setInt(3, item.getQuantity());
            ps.setBigDecimal(4, item.getUnitPrice());
            ps.setInt(5, item.getProductSizeId());
            ps.setInt(6, item.getCartItemId());

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public boolean updateQuantity(int cartItemId, int quantity) {
        String sql = "UPDATE cart_items SET quantity=? WHERE cart_item_id=?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, cartItemId);

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public boolean incrementQuantity(int cartId, int productId, int productSizeId) {
        String sql = "UPDATE cart_items SET quantity = quantity + 1 WHERE cart_id=? AND product_id=? AND product_size_id=?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            ps.setInt(3, productSizeId);

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public boolean decrementQuantity(int cartItemId) {
        String sql = "UPDATE cart_items SET quantity = quantity - 1 WHERE cart_item_id=? AND quantity > 1";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartItemId);
            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public double getCartTotal(int cartId) {
        String sql = "SELECT SUM(quantity * unit_price) AS total FROM cart_items WHERE cart_id=?";
        double total = 0;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getDouble("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    @Override
    public int getCartItemCount(int cartId) {
        String sql = "SELECT COUNT(*) FROM cart_items WHERE cart_id=?";
        int count = 0;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartId);
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
    public boolean delete(int cartItemId) {

        String sql = """
                DELETE FROM cart_items
                WHERE cart_item_id = ?
                """;

        boolean deleted = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, cartItemId);

            deleted = ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return deleted;
    }

    @Override
    public boolean deleteByCartId(int cartId) {
        String sql = "DELETE FROM cart_items WHERE cart_id=?";
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
    
    

    private CartItem mapRow(ResultSet rs) throws SQLException {
        CartItem item = new CartItem();

        item.setCartItemId(rs.getInt("cart_item_id"));
        item.setCartId(rs.getInt("cart_id"));
        item.setProductId(rs.getInt("product_id"));
        item.setQuantity(rs.getInt("quantity"));
        item.setUnitPrice(rs.getBigDecimal("unit_price"));
        item.setAddedAt(rs.getTimestamp("added_at"));
        item.setProductSizeId(rs.getInt("product_size_id"));

        return item;
    }

    @Override
    public CartItem findByCartAndProductSize(
            int cartId,
            int productSizeId
    ) {

        String sql = """
                SELECT *
                FROM cart_items
                WHERE cart_id = ?
                AND product_size_id = ?
                """;

        CartItem item = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, cartId);

            ps.setInt(2, productSizeId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                item = new CartItem();

                item.setCartItemId(
                        rs.getInt("cart_item_id")
                );

                item.setCartId(
                        rs.getInt("cart_id")
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
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return item;
    }
}