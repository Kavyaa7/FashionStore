<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>${product.productName} | FashionStore</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/product-details.css">

    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@400;500;600;700;800&display=swap"
      rel="stylesheet">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

</head>

<body>


<nav class="navbar">

    <div class="logo">
        <a href="${pageContext.request.contextPath}/">
            FashionStore
        </a>
    </div>

    <div class="nav-links">

        <a href="${pageContext.request.contextPath}/wishlist">

    <i class="fa-regular fa-heart"></i>

    Wishlist

</a>

<a href="${pageContext.request.contextPath}/cart">

    <i class="fa-solid fa-cart-shopping"></i>

    Cart

</a>

<a href="#">

    <i class="fa-regular fa-user"></i>

    Account

</a>

    </div>

</nav>


<div class="product-container">


    <div class="product-image-section">

        <img src="${product.imageUrl}"
             alt="${product.productName}">

    </div>


    <div class="product-details-section">

        <p class="product-category">
            ${product.category.categoryName}
        </p>

        <h1 class="product-name">
            ${product.productName}
        </h1>

        <div class="price-section">

            <span class="current-price">
                ₹${product.price}
            </span>

            <c:if test="${product.discountPercent > 0}">

                <span class="discount">
                    ${product.discountPercent}% OFF
                </span>

            </c:if>

        </div>
        
        <div class="stock-section">

    <c:choose>

        <c:when test="${product.stockQuantity > 5}">

            <p class="in-stock">

                In Stock (${product.stockQuantity} available)

            </p>

        </c:when>

        <c:when test="${product.stockQuantity > 0}">

            <p class="low-stock">

                Only ${product.stockQuantity} left!

            </p>

        </c:when>

        <c:otherwise>

            <p class="out-stock">

                Out of Stock

            </p>

        </c:otherwise>

    </c:choose>

</div>

        <div class="description-section">

            <h3>Description</h3>

            <p>
                ${product.description}
            </p>

        </div>
        
        

        


       <form action="${pageContext.request.contextPath}/add-to-cart"
      method="post">

    <input type="hidden"
           name="productId"
           value="${product.productId}">

  

    <div class="size-section">

        <h3>Select Size</h3>

        <div class="sizes-container">

            <c:forEach var="size" items="${sizes}">

                <label class="size-label">

                    <input type="radio"
                           name="productSizeId"
                           value="${size.productSizeId}"
                           required>

                    <span class="size-btn">
                        ${size.sizeLabel}
                    </span>

                </label>

            </c:forEach>

        </div>

    </div>


    <div class="quantity-section">

        <h3>Quantity</h3>

        <input type="number"
               name="quantity"
               value="1"
               min="1"
               class="quantity-input">

    </div>

	<form method="post" action="${pageContext.request.contextPath}/add-to-wishlist">

    <input type="hidden"
           name="productId"
           value="${product.productId}">

    <c:choose>

    <c:when test="${inWishlist}">

        <button
        	type="submit"
            class="wishlist-btn"
            disabled>

            ❤️ Added to Wishlist

        </button>

    </c:when>

    <c:otherwise>

        <button
        type="submit"
            class="wishlist-btn">

            🤍 Add To Wishlist

        </button>

    </c:otherwise>

</c:choose>

</form>

    <div class="action-buttons">

	<c:choose>

    <c:when test="${product.stockQuantity > 0}">

        <button type="submit"
        name="action"
        value="cart"
        class="add-cart-btn">
        
        <i class="fa-solid fa-cart-shopping"></i>

            Add To Cart

        </button>

    </c:when>

    <c:otherwise>

        <button class="add-cart-btn"
                disabled>

            Out Of Stock

        </button>

    </c:otherwise>

</c:choose>


    </div>

</form>

    </div>

</div>

</body>

</html>