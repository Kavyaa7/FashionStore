<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="com.fashionstore.model.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>FashionStore</title>

	<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/home.css">
    
    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@400;500;600;700;800&display=swap" rel="stylesheet">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>

<body>

<!-- ================= NAVBAR ================= -->

<nav class="navbar">

    <div class="logo">
        <h2>FashionStore</h2>
    </div>

    <form class="search-box"
      method="get"
      action="${pageContext.request.contextPath}/home">

    <input type="text"
           name="keyword"
           placeholder="Search for products..."
           value="${param.keyword}">

    <button type="submit">

        <i class="fa-solid fa-magnifying-glass"></i>

    </button>

</form>

    <div class="nav-links">

    <c:choose>

        <c:when test="${sessionScope.loggedInUser != null}">

            <span class="welcome-text">

                Hi,
                ${sessionScope.loggedInUser.fullName}

            </span>

            <a href="${pageContext.request.contextPath}/cart">

                <i class="fa-solid fa-cart-shopping"></i>

                Cart

            </a>
            
                        <a href="${pageContext.request.contextPath}/my-orders">

                <i class="fa-solid fa-box"></i>

                My Orders

            </a>
            

            <a href="${pageContext.request.contextPath}/logout">

                Logout

            </a>

        </c:when>

        <c:otherwise>

            <a href="${pageContext.request.contextPath}/views/user/login.jsp">

                Login

            </a>

            <a href="${pageContext.request.contextPath}/views/user/register.jsp">

                Register

            </a>

        </c:otherwise>

    </c:choose>

</div>

</nav>



<div class="main-container">


    <aside class="sidebar">

        <h3>Categories</h3>

        <ul class="category-list">

            <li>
        <a href="home">All Products</a>
    </li>

    <c:forEach var="category" items="${categories}">

        <li>
            <a href="#">
                ${category.categoryName}
            </a>
        </li>

    </c:forEach>

        </ul>

        <form method="get"
      action="${pageContext.request.contextPath}/home">

    <!-- CATEGORY FILTER -->

    <div class="filter-group">

        <label>Category</label>

       <select name="categoryId">

    <option value="">All</option>

    <option value="1"
        ${param.categoryId == '1' ? 'selected' : ''}>

        Men Clothing

    </option>

    <option value="2"
        ${param.categoryId == '2' ? 'selected' : ''}>

        Women Clothing

    </option>

    <option value="3"
        ${param.categoryId == '3' ? 'selected' : ''}>

        Footwear

    </option>

    <option value="4"
        ${param.categoryId == '4' ? 'selected' : ''}>

        Electronics

    </option>

</select>

    </div>

    <!-- PRICE FILTER -->

    <div class="filter-group">

        <label>Price</label>

        <select name="priceRange">

            <option value="">All</option>

            <option value="0-1000"   ${param.categoryId == '0-1000' ? 'selected' : ''}>
                Below ₹1000
            </option>

            <option value="1000-3000"         ${param.categoryId == '1000-3000' ? 'selected' : ''}>
                ₹1000 - ₹3000
            </option>

            <option value="3000-10000"         ${param.categoryId == '3000-10000' ? 'selected' : ''}>
                ₹3000 - ₹10000
            </option>

            <option value="10000+"         ${param.categoryId == '10000+' ? 'selected' : ''}>
                Above ₹10000
            </option>

        </select>

    </div>

    <button class="filter-btn">
        Apply Filters
    </button>
    
    <div class="filter-group">

    <label>Sort By</label>

    <select name="sortBy">

        <option value="" >Default</option>

        <option value="priceAsc"         ${param.categoryId == 'priceAsc' ? 'selected' : ''}>
            Price Low to High
        </option>

        <option value="priceDesc"         ${param.categoryId == 'priceDesc' ? 'selected' : ''}>
            Price High to Low
        </option>

        <option value="latest"         ${param.categoryId == 'latest' ? 'selected' : ''}>
            Newest First
        </option>

        <option value="nameAsc"         ${param.categoryId == 'nameAsc' ? 'selected' : ''}>
            Name A-Z
        </option>

    </select>

</div>

</form>

    </aside>

    <!-- ================= PRODUCT SECTION ================= -->

    <section class="products-section">

        <div class="section-header">
            <h2>Trending Products</h2>
        </div>

     	<div class="products-grid">

    <c:forEach var="product" items="${products}">

        <a class="product-link"
   href="${pageContext.request.contextPath}/product-details?id=${product.productId}">

    <div class="product-card">

            <div class="product-image">

                <img src="${product.imageUrl}"
                     alt="Product">

            </div>

            <div class="product-info">

                <h3>${product.productName}</h3>

                <p class="category">
				${product.category.categoryName}
                </p>

                <div class="price-section">

                    <span class="price">
                        ₹${product.price}
                    </span>

                </div>

                <button class="cart-btn">
                    Add to Cart
                </button>

            </div>

        </div>
       </a>

    </c:forEach>

</div>

    </section>

</div>

</body>
</html>