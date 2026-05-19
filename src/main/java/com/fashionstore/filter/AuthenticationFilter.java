package com.fashionstore.filter;

import com.fashionstore.model.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({
        "/cart",
        "/add-to-cart",
        "/update-cart-quantity",
        "/remove-from-cart"
})
public class AuthenticationFilter
        extends HttpFilter
        implements Filter {

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain)
            throws IOException, ServletException {

        HttpSession session =
                request.getSession(false);

        User loggedInUser = null;

        if (session != null) {

            loggedInUser =
                    (User) session.getAttribute(
                            "loggedInUser"
                    );
        }

        if (loggedInUser == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/views/user/login.jsp"
            );

            return;
        }

        chain.doFilter(request, response);
    }
}