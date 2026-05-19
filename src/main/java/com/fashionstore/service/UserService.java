package com.fashionstore.service;
import com.fashionstore.model.User;

public interface UserService {
	 int register(User user);
	 
	 User login(String email, String password);
}
