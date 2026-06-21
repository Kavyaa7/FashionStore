<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Checkout | FashionStore</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/checkout.css">

    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@400;500;600;700;800&display=swap"
      rel="stylesheet">

</head>

<body>

<div class="checkout-container">

    <!-- SHIPPING -->

    <div class="checkout-left">

        <h1>Checkout</h1>

        <form action="${pageContext.request.contextPath}/place-order"
              method="post">

            <div class="form-group">

                <label>Full Name</label>

                <input type="text"
                       name="fullName"
                       required>

            </div>

            <div class="form-group">

                <label>Phone Number</label>

                <input type="text"
                       name="phone"
                       required>

            </div>

            <div class="form-group">

                <label>Shipping Address</label>

                <textarea name="shippingAddress"
                          rows="5"
                          required></textarea>

            </div>

            <div class="form-group">

                <label>Payment Method</label>

                <select name="paymentMethod">

                    <option value="COD">

                        Cash On Delivery

                    </option>

                    <option value="UPI">

                        UPI

                    </option>

                    <option value="CARD">

                        Debit/Credit Card

                    </option>

                </select>

            </div>

            <button type="submit"
                    class="place-order-btn">

                Place Order

            </button>

        </form>

    </div>

    <!-- ORDER SUMMARY -->

    <div class="checkout-right">

        <h2>Order Summary</h2>

        <div class="summary-items">

            <c:forEach var="item"
                       items="${cartItems}">

                <div class="summary-item">

                    <img src="${item.imageUrl}"
                         alt="${item.productName}">

                    <div>

                        <h4>${item.productName}</h4>

                        <p>

                            Qty:
                            ${item.quantity}

                        </p>

                    </div>

                    <span>

                        ₹${item.price}

                    </span>

                </div>

            </c:forEach>

        </div>

        <div class="summary-total">

            <h3>

                Total:
                ₹${totalAmount}

            </h3>

        </div>

    </div>

</div>

</body>

</html>