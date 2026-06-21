package com.fashionstore.dao.impl;

import com.fashionstore.dao.WishlistDAO;
import com.fashionstore.model.Wishlist;
import com.fashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAOImpl implements WishlistDAO {

    @Override
    public boolean exists(int userId, int productId) {

        String sql = """
                SELECT * FROM wishlist
                WHERE user_id = ?
                AND product_id = ?
                """;

        try(Connection conn =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, productId);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    @Override
    public int save(Wishlist wishlist) {
    	
    	System.out.println("Saving wishlist...");
    	
        String sql = """
                INSERT INTO wishlist(
                    user_id,
                    product_id
                )
                VALUES(?, ?)
                """;

        try(Connection conn =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(
                            sql,
                            Statement.RETURN_GENERATED_KEYS
                    )) {

            ps.setInt(1, wishlist.getUserId());
            ps.setInt(2, wishlist.getProductId());

            ps.executeUpdate();

            System.out.println("Rows inserted");
            
            ResultSet rs =
                    ps.getGeneratedKeys();

            if(rs.next()) {

                return rs.getInt(1);
            }

        } catch(Exception e) {

        	System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Wishlist> findByUserId(int userId) {

        List<Wishlist> list =
                new ArrayList<>();

        String sql = """
                SELECT w.*,
                       p.product_name,
                       p.image_url,
                       p.price,
                       c.category_name
                FROM wishlist w

                JOIN products p
                ON w.product_id = p.product_id

                JOIN categories c
                ON p.category_id = c.category_id

                WHERE w.user_id = ?

                ORDER BY w.created_at DESC
                """;

        try(Connection conn =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Wishlist wishlist =
                        new Wishlist();

                wishlist.setWishlistId(
                        rs.getInt("wishlist_id")
                );

                wishlist.setUserId(
                        rs.getInt("user_id")
                );

                wishlist.setProductId(
                        rs.getInt("product_id")
                );

                wishlist.setCreatedAt(
                        rs.getTimestamp("created_at")
                );

                wishlist.setProductName(
                        rs.getString("product_name")
                );

                wishlist.setImageUrl(
                        rs.getString("image_url")
                );

                wishlist.setCategoryName(
                        rs.getString("category_name")
                );

                wishlist.setPrice(
                        rs.getBigDecimal("price")
                );

                list.add(wishlist);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean remove(
            int userId,
            int productId
    ) {

        String sql = """
                DELETE FROM wishlist
                WHERE user_id = ?
                AND product_id = ?
                """;

        try(Connection conn =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, productId);

            return ps.executeUpdate() > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return false;
    }
}