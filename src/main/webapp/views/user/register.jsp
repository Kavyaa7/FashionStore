<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Register | FashionStore</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/auth.css">

    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@400;500;600;700;800&display=swap"
      rel="stylesheet">

</head>

<body>

<div class="auth-container">

    <div class="auth-card">

        <h1>Create Account</h1>

        <form action="${pageContext.request.contextPath}/register"
              method="post">

            <input type="text"
                   name="fullName"
                   placeholder="Full Name"
                   required>

            <input type="email"
                   name="email"
                   placeholder="Email Address"
                   required>

            <input type="password"
                   name="password"
                   placeholder="Password"
                   required>

            <input type="text"
                   name="phone"
                   placeholder="Phone Number"
                   required>

            <button type="submit">

                Register

            </button>

        </form>

        <p>

            Already have an account?

            <a href="${pageContext.request.contextPath}/views/user/login.jsp">

                Login

            </a>

        </p>

    </div>

</div>

</body>

</html>