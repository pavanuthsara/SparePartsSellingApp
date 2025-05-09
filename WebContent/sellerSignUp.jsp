<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Seller sign up page</h1>

<!-- Sign up form for seller -->
<form action="">

    <label for="">Email</label>
    <input type="text" name="email" required>

    <label for="">Password</label>
    <input type="password" name="password" required>

    <label for="">Re-enter Password</label>
    <input type="password" name="rePassword" required>

    <label for="">Address</label>
    <input type="text" name="address" required>

    <label for="">Mobile no</label>
    <input type="text" name="mobileNumber" required>

    <label for="">Store name</label>
    <input type="text" name="storeName" required>

    <input type="checkbox" name="conditions" value="accepted">
    <label for="">I have accepted User terms and conditions</label>

    <button type="submit">Sign Up</button>
</form>
</body>
</html>