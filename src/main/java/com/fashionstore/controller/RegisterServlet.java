package com.fashionstore.controller;

import com.fashionstore.model.User;
import com.fashionstore.service.UserService;
import com.fashionstore.service.impl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {

        userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String fullName =
                    request.getParameter("fullName");

            String email =
                    request.getParameter("email");

            String password =
                    request.getParameter("password");

            String phone =
                    request.getParameter("phone");

            User user = new User();

            user.setFullName(fullName);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);

            int userId = userService.register(user);

            if (userId > 0) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/views/user/login.jsp"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                                + "/views/user/register.jsp"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                            + "/views/user/register.jsp"
            );
        }
    }
}