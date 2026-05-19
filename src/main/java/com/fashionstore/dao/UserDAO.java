package com.fashionstore.dao;

import com.fashionstore.model.User;
import java.util.List;

public interface UserDAO {

    int save(User user);

    User login(String email, String password);

    User findById(int userId);
    User findByEmail(String email);
    List<User> findAll();

    boolean existsByEmail(String email);

    boolean update(User user);

    boolean delete(int userId);
    
    User findByEmailAndPassword(
            String email,
            String password
    );
}