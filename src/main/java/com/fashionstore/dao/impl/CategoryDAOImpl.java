package com.fashionstore.dao.impl;

import com.fashionstore.dao.CategoryDAO;
import com.fashionstore.model.Category;
import com.fashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public int save(Category category) {
        String sql = "INSERT INTO categories (category_name, description, is_active) VALUES (?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getDescription());
            ps.setBoolean(3, category.isActive());

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
    public Category findById(int categoryId) {
        String sql = "SELECT * FROM categories WHERE category_id = ?";
        Category category = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                category = mapRow(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM categories";
        List<Category> list = new ArrayList<>();

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
    public List<Category> findActive() {
        String sql = "SELECT * FROM categories WHERE is_active = 1";
        List<Category> list = new ArrayList<>();

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
    public boolean update(Category category) {
        String sql = "UPDATE categories SET category_name=?, description=?, is_active=? WHERE category_id=?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getDescription());
            ps.setBoolean(3, category.isActive());
            ps.setInt(4, category.getCategoryId());

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public boolean delete(int categoryId) {
        String sql = "DELETE FROM categories WHERE category_id = ?";
        boolean deleted = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, categoryId);
            deleted = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }

    private Category mapRow(ResultSet rs) throws SQLException {
        Category category = new Category();

        category.setCategoryId(rs.getInt("category_id"));
        category.setCategoryName(rs.getString("category_name"));
        category.setDescription(rs.getString("description"));
        category.setActive(rs.getBoolean("is_active"));

        return category;
    }
}