package com.fashionstore.dao.impl;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.model.Category;
import com.fashionstore.model.Product;
import com.fashionstore.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public int save(Product product) {
        String sql = "INSERT INTO products (category_id, product_name, description, discount_percent, image_url, is_active, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, product.getCategoryId());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getDescription());
            ps.setBigDecimal(4, product.getDiscountPercent());
            ps.setString(5, product.getImageUrl());
            ps.setBoolean(6, product.isActive());
            ps.setBigDecimal(7, product.getPrice());

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
    public Product findById(int productId) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        Product product = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                product = mapRow(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public List<Product> findAll() {
    	String sql = """
    		    SELECT p.*, c.category_name
    		    FROM products p
    		    JOIN categories c
    		    ON p.category_id = c.category_id
    		    """;
        List<Product> list = new ArrayList<>();

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
    public List<Product> findActive() {
    	String sql = """
    		    SELECT p.*, c.category_name
    		    FROM products p
    		    JOIN categories c
    		    ON p.category_id = c.category_id
    		    WHERE p.is_active = 1
    		    """;
    	
    	List<Product> list = new ArrayList<>();

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
    public List<Product> findByCategory(int categoryId) {
        String sql = "SELECT * FROM products WHERE category_id = ?";
        List<Product> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, categoryId);
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
    public List<Product> searchByName(String keyword) {
        String sql = "SELECT * FROM products WHERE product_name LIKE ?";
        List<Product> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
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
    public List<Product> filterProducts(Integer categoryId, BigDecimal minPrice,
                                        BigDecimal maxPrice, Boolean isActive) {

        StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE 1=1");
        List<Product> list = new ArrayList<>();

        if (categoryId != null) sql.append(" AND category_id = ?");
        if (minPrice != null) sql.append(" AND price >= ?");
        if (maxPrice != null) sql.append(" AND price <= ?");
        if (isActive != null) sql.append(" AND is_active = ?");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int index = 1;

            if (categoryId != null) ps.setInt(index++, categoryId);
            if (minPrice != null) ps.setBigDecimal(index++, minPrice);
            if (maxPrice != null) ps.setBigDecimal(index++, maxPrice);
            if (isActive != null) ps.setBoolean(index++, isActive);

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
    public boolean update(Product product) {
        String sql = "UPDATE products SET category_id=?, product_name=?, description=?, discount_percent=?, image_url=?, is_active=?, price=? WHERE product_id=?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, product.getCategoryId());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getDescription());
            ps.setBigDecimal(4, product.getDiscountPercent());
            ps.setString(5, product.getImageUrl());
            ps.setBoolean(6, product.isActive());
            ps.setBigDecimal(7, product.getPrice());
            ps.setInt(8, product.getProductId());

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public boolean delete(int productId) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        boolean deleted = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            deleted = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }

    private Product mapRow(ResultSet rs) throws SQLException {

        Product product = new Product();

        product.setProductId(rs.getInt("product_id"));
        product.setCategoryId(rs.getInt("category_id"));
        product.setProductName(rs.getString("product_name"));
        product.setDescription(rs.getString("description"));
        product.setDiscountPercent(rs.getBigDecimal("discount_percent"));
        product.setImageUrl(rs.getString("image_url"));
        product.setActive(rs.getBoolean("is_active"));
        product.setPrice(rs.getBigDecimal("price"));

        Category category = new Category();

        category.setCategoryId(rs.getInt("category_id"));

        try {
            category.setCategoryName(rs.getString("category_name"));
        } catch (SQLException e) {

        }

        product.setCategory(category);

        return product;
    }

    @Override
    public List<Product> findLatest(int limit) {
    	String sql = """
    		    SELECT p.*, c.category_name
    		    FROM products p
    		    JOIN categories c
    		    ON p.category_id = c.category_id
    		    ORDER BY p.product_id DESC
    		    LIMIT ?
    		    """;
    	List<Product> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, limit);
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
    public List<Product> findByPriceRange(BigDecimal min, BigDecimal max) {
        String sql = "SELECT * FROM products WHERE price BETWEEN ? AND ?";
        List<Product> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBigDecimal(1, min);
            ps.setBigDecimal(2, max);

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
    public List<Product> findByCategoryAndActive(int categoryId) {
        String sql = "SELECT * FROM products WHERE category_id = ? AND is_active = 1";
        List<Product> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, categoryId);
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
    public List<Product> searchAndFilter(String keyword,
                                         Integer categoryId,
                                         BigDecimal minPrice,
                                         BigDecimal maxPrice,
                                         Boolean isActive,
                                         String sortBy) {

        StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE 1=1");
        List<Product> list = new ArrayList<>();

        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND product_name LIKE ?");
        }
        if (categoryId != null) {
            sql.append(" AND category_id = ?");
        }
        if (minPrice != null) {
            sql.append(" AND price >= ?");
        }
        if (maxPrice != null) {
            sql.append(" AND price <= ?");
        }
        if (isActive != null) {
            sql.append(" AND is_active = ?");
        }

        // Sorting
        if ("price_asc".equalsIgnoreCase(sortBy)) {
            sql.append(" ORDER BY price ASC");
        } else if ("price_desc".equalsIgnoreCase(sortBy)) {
            sql.append(" ORDER BY price DESC");
        } else {
            sql.append(" ORDER BY product_id DESC");
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int index = 1;

            if (keyword != null && !keyword.isEmpty()) {
                ps.setString(index++, "%" + keyword + "%");
            }
            if (categoryId != null) {
                ps.setInt(index++, categoryId);
            }
            if (minPrice != null) {
                ps.setBigDecimal(index++, minPrice);
            }
            if (maxPrice != null) {
                ps.setBigDecimal(index++, maxPrice);
            }
            if (isActive != null) {
                ps.setBoolean(index++, isActive);
            }

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
    public List<Product> filterProducts(
            String categoryId,
            String priceRange
    ) {

        List<Product> products = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""

            SELECT p.*, c.category_name
            FROM products p
            JOIN categories c
            ON p.category_id = c.category_id
            WHERE p.is_active = 1

        """);

        List<Object> params = new ArrayList<>();

        /*
         * CATEGORY FILTER
         */

        if(categoryId != null &&
           !categoryId.isEmpty()) {

            sql.append(" AND p.category_id = ? ");

            params.add(
                    Integer.parseInt(categoryId)
            );
        }

        /*
         * PRICE FILTER
         */

        if(priceRange != null &&
           !priceRange.isEmpty()) {

            switch(priceRange) {

                case "0-1000":

                    sql.append(
                        " AND p.price < ? "
                    );

                    params.add(1000);

                    break;

                case "1000-3000":

                    sql.append(
                        " AND p.price BETWEEN ? AND ? "
                    );

                    params.add(1000);
                    params.add(3000);

                    break;

                case "3000-10000":

                    sql.append(
                        " AND p.price BETWEEN ? AND ? "
                    );

                    params.add(3000);
                    params.add(10000);

                    break;

                case "10000+":

                    sql.append(
                        " AND p.price > ? "
                    );

                    params.add(10000);

                    break;
            }
        }

        try(Connection conn =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(
                            sql.toString()
                    )) {

            /*
             * SET PARAMETERS
             */

            for(int i = 0; i < params.size(); i++) {

                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                products.add(mapRow(rs));
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return products;
    }
    
    @Override
    public List<Product> searchFilterSortProducts(
            String categoryId,
            String priceRange,
            String keyword,
            String sortBy
    ) {

        List<Product> products = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""

            SELECT p.*, c.category_name
            FROM products p
            JOIN categories c
            ON p.category_id = c.category_id
            WHERE p.is_active = 1

        """);

        List<Object> params = new ArrayList<>();

        /*
         * CATEGORY FILTER
         */

        if(categoryId != null &&
           !categoryId.isEmpty()) {

            sql.append(
                    " AND p.category_id = ? "
            );

            params.add(
                    Integer.parseInt(categoryId)
            );
        }

        /*
         * PRICE FILTER
         */

        if(priceRange != null &&
           !priceRange.isEmpty()) {

            switch(priceRange) {

                case "0-1000":

                    sql.append(
                            " AND p.price < ? "
                    );

                    params.add(1000);

                    break;

                case "1000-3000":

                    sql.append(
                            " AND p.price BETWEEN ? AND ? "
                    );

                    params.add(1000);
                    params.add(3000);

                    break;

                case "3000-10000":

                    sql.append(
                            " AND p.price BETWEEN ? AND ? "
                    );

                    params.add(3000);
                    params.add(10000);

                    break;

                case "10000+":

                    sql.append(
                            " AND p.price > ? "
                    );

                    params.add(10000);

                    break;
            }
        }

        /*
         * SEARCH
         */

        if(keyword != null &&
           !keyword.trim().isEmpty()) {

            sql.append(
                    " AND LOWER(p.product_name) LIKE ? "
            );

            params.add(
                    "%" +
                    keyword.toLowerCase() +
                    "%"
            );
        }

        /*
         * SORTING
         */

        if(sortBy != null &&
           !sortBy.isEmpty()) {

            switch(sortBy) {

                case "priceAsc":

                    sql.append(
                            " ORDER BY p.price ASC "
                    );

                    break;

                case "priceDesc":

                    sql.append(
                            " ORDER BY p.price DESC "
                    );

                    break;

                case "latest":

                    sql.append(
                            " ORDER BY p.product_id DESC "
                    );

                    break;

                case "nameAsc":

                    sql.append(
                            " ORDER BY p.product_name ASC "
                    );

                    break;

                default:

                    sql.append(
                            " ORDER BY p.product_id DESC "
                    );
            }

        } else {

            sql.append(
                    " ORDER BY p.product_id "
            );
        }

        try(Connection conn =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(
                            sql.toString()
                    )) {

            /*
             * SET PARAMETERS
             */

            for(int i = 0; i < params.size(); i++) {

                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                products.add(mapRow(rs));
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return products;
    }

	@Override
	public int countProducts() {
		// TODO Auto-generated method stub
		return 0;
	}
}