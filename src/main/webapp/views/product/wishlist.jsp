<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>

<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width,
                   initial-scale=1.0">

    <title>Wishlist | FashionStore</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/wishlist.css">
    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@400;500;600;700;800&display=swap"
      rel="stylesheet">

</head>

<body>

<div class="wishlist-container">

    <h1>My Wishlist</h1>

    <div class="wishlist-grid">

        <c:forEach var="item"
                   items="${wishlistItems}">

            <div class="wishlist-card">

                <img src="${item.imageUrl}"
                     alt="${item.productName}">

                <h3>${item.productName}</h3>

                <p>${item.categoryName}</p>

                <h4>₹${item.price}</h4>

                <form method="post"
                      action="${pageContext.request.contextPath}/remove-wishlist">

                    <input type="hidden"
                           name="productId"
                           value="${item.productId}">

                    <button type="submit">

                        Remove

                    </button>

                </form>

            </div>

        </c:forEach>

    </div>

</div>

</body>

</html>