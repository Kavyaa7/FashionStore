package com.fashionstore.controller;

import com.fashionstore.model.User;
import com.fashionstore.service.UserService;
import com.fashionstore.service.impl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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

            String email =
                    request.getParameter("email");

            String password =
                    request.getParameter("password");

            User user =
                    userService.login(email, password);

            if (user != null) {

                HttpSession session =
                        request.getSession();

                session.setAttribute(
                        "loggedInUser",
                        user
                );

                response.sendRedirect(
                        request.getContextPath()
                                + "/home"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                                + "/views/user/login.jsp"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                            + "/views/user/login.jsp"
            );
        }
    }
}