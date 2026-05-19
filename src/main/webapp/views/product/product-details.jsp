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

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
          rel="stylesheet">

    <!-- Font Awesome -->
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

        <a href="#">
            <i class="fa-regular fa-user"></i>
            Account
        </a>

        <a href="#">
            <i class="fa-solid fa-cart-shopping"></i>
            Cart
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


    <div class="action-buttons">

        <button type="submit"
        name="action"
        value="cart"
        class="add-cart-btn">

    <i class="fa-solid fa-cart-shopping"></i>

    Add To Cart

</button>

<button type="submit"
        name="action"
        value="buyNow"
        class="buy-now-btn">

    Buy Now

</button>

    </div>

</form>

    </div>

</div>

</body>

</html>