<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Login | FashionStore</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/auth.css">

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
          rel="stylesheet">

</head>

<body>

<div class="auth-container">

    <div class="auth-card">

        <h1>Welcome Back</h1>

        <form action="${pageContext.request.contextPath}/login"
              method="post">

            <input type="email"
                   name="email"
                   placeholder="Email Address"
                   required>

            <input type="password"
                   name="password"
                   placeholder="Password"
                   required>

            <button type="submit">

                Login

            </button>

        </form>

        <p>

            Don't have an account?

            <a href="${pageContext.request.contextPath}/views/user/register.jsp">

                Register

            </a>

        </p>

    </div>

</div>

</body>

</html>