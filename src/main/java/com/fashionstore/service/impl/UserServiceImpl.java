package com.fashionstore.service.impl;

import com.fashionstore.dao.UserDAO;
import com.fashionstore.dao.impl.UserDAOImpl;
import com.fashionstore.model.User;
import com.fashionstore.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl() {

        userDAO = new UserDAOImpl();
    }

    @Override
    public int register(User user) {

        return userDAO.save(user);
    }
    
    @Override
    public User login(String email,
                      String password) {

        return userDAO.findByEmailAndPassword(
                email,
                password
        );
    }
}