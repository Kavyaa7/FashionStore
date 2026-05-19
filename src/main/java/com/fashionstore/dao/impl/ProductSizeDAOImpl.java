package com.fashionstore.dao.impl;

import com.fashionstore.dao.ProductSizeDAO;
import com.fashionstore.model.ProductSize;
import com.fashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSizeDAOImpl implements ProductSizeDAO {

    @Override
    public int save(ProductSize productSize) {
        String sql = "INSERT INTO product_sizes (product_id, size_label, stock_quantity, sku_code, is_available) VALUES (?, ?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, productSize.getProductId());
            ps.setString(2, productSize.getSizeLabel());
            ps.setInt(3, productSize.getStockQuantity());
            ps.setString(4, productSize.getSkuCode());
            ps.setBoolean(5, productSize.isAvailable());

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
    public ProductSize findById(int productSizeId) {
        String sql = "SELECT * FROM product_sizes WHERE product_size_id = ?";
        ProductSize psObj = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productSizeId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                psObj = mapRow(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return psObj;
    }

    @Override
    public List<ProductSize> findByProductId(int productId) {
        String sql = "SELECT * FROM product_sizes WHERE product_id = ?";
        List<ProductSize> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
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
    public List<ProductSize> findAvailableByProductId(int productId) {
        String sql = "SELECT * FROM product_sizes WHERE product_id = ? AND is_available = 1";
        List<ProductSize> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
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
    public ProductSize findByProductAndSize(int productId, String sizeLabel) {
        String sql = "SELECT * FROM product_sizes WHERE product_id = ? AND size_label = ?";
        ProductSize psObj = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            ps.setString(2, sizeLabel);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                psObj = mapRow(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return psObj;
    }

    @Override
    public boolean update(ProductSize productSize) {
        String sql = "UPDATE product_sizes SET product_id=?, size_label=?, stock_quantity=?, sku_code=?, is_available=? WHERE product_size_id=?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productSize.getProductId());
            ps.setString(2, productSize.getSizeLabel());
            ps.setInt(3, productSize.getStockQuantity());
            ps.setString(4, productSize.getSkuCode());
            ps.setBoolean(5, productSize.isAvailable());
            ps.setInt(6, productSize.getProductSizeId());

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public boolean updateStock(int productSizeId, int quantity) {
        String sql = "UPDATE product_sizes SET stock_quantity = ? WHERE product_size_id = ?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, productSizeId);

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public boolean reduceStock(int productSizeId, int quantity) {
        String sql = "UPDATE product_sizes SET stock_quantity = stock_quantity - ? WHERE product_size_id = ? AND stock_quantity >= ?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, productSizeId);
            ps.setInt(3, quantity);

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public boolean delete(int productSizeId) {
        String sql = "DELETE FROM product_sizes WHERE product_size_id = ?";
        boolean deleted = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productSizeId);
            deleted = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }

    private ProductSize mapRow(ResultSet rs) throws SQLException {
        ProductSize psObj = new ProductSize();

        psObj.setProductSizeId(rs.getInt("product_size_id"));
        psObj.setProductId(rs.getInt("product_id"));
        psObj.setSizeLabel(rs.getString("size_label"));
        psObj.setStockQuantity(rs.getInt("stock_quantity"));
        psObj.setSkuCode(rs.getString("sku_code"));
        psObj.setAvailable(rs.getBoolean("is_available"));

        return psObj;
    }
}