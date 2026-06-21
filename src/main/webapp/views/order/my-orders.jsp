<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>My Orders</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/my-orders.css">

    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@400;500;600;700;800&display=swap"
      rel="stylesheet">

</head>

<body>

<div class="orders-container">

    <h1>My Orders</h1>

    <c:forEach var="order"
               items="${orders}">

        <div class="order-card">

            <div class="order-header">

                <div>

                    <h3>

                        Order #${order.orderId}

                    </h3>

                    <p>

                        ${order.orderDate}

                    </p>

                </div>

                <div>

                    <span class="status">

                        ${order.orderStatus}

                    </span>

                </div>

            </div>

            <div class="order-items">

                <c:forEach var="item"
                           items="${order.orderItems}">

                    <div class="order-item">

                        <img src="${item.imageUrl}"
                             alt="${item.productName}">

                        <div>

                            <h4>

                                ${item.productName}

                            </h4>

                            <p>

                                Size:
                                ${item.sizeLabel}

                            </p>

                            <p>

                                Qty:
                                ${item.quantity}

                            </p>

                        </div>

                        <span>

                            ₹${item.unitPrice}

                        </span>

                    </div>

                </c:forEach>

            </div>

            <div class="order-footer">

                <h3>

                    Total:
                    ₹${order.totalAmount}

                </h3>

            </div>

        </div>

    </c:forEach>

</div>

</body>

</html>