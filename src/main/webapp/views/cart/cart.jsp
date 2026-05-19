<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>My Cart | FashionStore</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/cart.css">

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
          rel="stylesheet">

</head>

<body>

<div class="cart-container">

    <h1>Shopping Cart</h1>

    <c:choose>

        <c:when test="${empty cartItems}">

            <div class="empty-cart">

                <h2>Your cart is empty</h2>

                <a href="${pageContext.request.contextPath}/home">

                    Continue Shopping

                </a>

            </div>

        </c:when>

        <c:otherwise>

            <div class="cart-items">

                <c:forEach var="item"
                           items="${cartItems}">

                    <div class="cart-item">

                        <img src="${item.imageUrl}"
                             alt="${item.productName}">

                        <div class="item-details">

                            <h3>${item.productName}</h3>

                            <p>
                                Size:
                                ${item.sizeLabel}
                            </p>

                            <div class="quantity-controls">

    <!-- DECREASE -->

    <form action="${pageContext.request.contextPath}/update-cart-quantity"
          method="post">

        <input type="hidden"
               name="cartItemId"
               value="${item.cartItemId}">

        <input type="hidden"
               name="action"
               value="decrease">

        <button type="submit"
                class="qty-btn">

            -

        </button>

    </form>

    <!-- QUANTITY -->

    <span class="quantity-value">

        ${item.quantity}

    </span>

    <!-- INCREASE -->

    <form action="${pageContext.request.contextPath}/update-cart-quantity"
          method="post">

        <input type="hidden"
               name="cartItemId"
               value="${item.cartItemId}">

        <input type="hidden"
               name="action"
               value="increase">

        <button type="submit"
                class="qty-btn">

            +

        </button>

    </form>

</div>

                            <h4>
                                ₹${item.price}
                            </h4>
                            
                            <form action="${pageContext.request.contextPath}/remove-from-cart" method="post">

    							<input type="hidden" name="cartItemId" value="${item.cartItemId}">
								<button type="submit" class="remove-btn">Remove
 								 </button>

							</form>

                        </div>

                    </div>

                </c:forEach>

            </div>

            <div class="cart-summary">

                <h2>

                    Total:
                    ₹${totalAmount}

                </h2>

                <a href="${pageContext.request.contextPath}/checkout"
   class="checkout-btn">

    Proceed To Checkout

</a>

            </div>

        </c:otherwise>

    </c:choose>

</div>

</body>

</html>