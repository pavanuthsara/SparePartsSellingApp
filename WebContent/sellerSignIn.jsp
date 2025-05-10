<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Seller Sign In page</h1>

<!-- Sign in form for seller -->
<form action="SellerSignIn" method="post">

    <label for="">Enter Email</label>
    <input type="text" name="email" required>

    <label for="">Enter Password</label>
    <input type="password" name="password" required>


    <button type="submit">Sign In</button>
</form>

</body>
</html>