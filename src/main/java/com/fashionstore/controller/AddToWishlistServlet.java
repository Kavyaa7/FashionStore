package com.fashionstore.controller;

import com.fashionstore.model.Wishlist;
import com.fashionstore.service.WishlistService;
import com.fashionstore.service.impl.WishlistServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/add-to-wishlist")
public class AddToWishlistServlet extends HttpServlet {

    private WishlistService wishlistService;

    @Override
    public void init() {

        wishlistService =
                new WishlistServiceImpl();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

    	System.out.println("AddToWishlistServlet called");
    	
    	HttpSession session = request.getSession(false);

    	Integer userId = (Integer) session.getAttribute("userId");

    	if (userId == null) {
    	    response.sendRedirect(request.getContextPath() + "/login");
    	    return;
    	}

        try {

            int productId =
                    Integer.parseInt(
                            request.getParameter(
                                    "productId"
                            )
                    );
            
            System.out.println("Product ID = " + productId);

            boolean exists =
                    wishlistService.exists(
                            userId,
                            productId
                    );
            System.out.println("Exists = " + exists);

            if(!exists) {

                Wishlist wishlist =
                        new Wishlist();

                wishlist.setUserId(userId);

                wishlist.setProductId(productId);

                wishlistService.save(wishlist);
            }

            response.sendRedirect(
                    request.getContextPath()
                    + "/wishlist"
            );

        } catch(Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/"
            );
        }
    }
}