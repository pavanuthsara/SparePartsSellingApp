<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>User Sign Up page</h1>

<h2>Need to become a seller? <a href="./sellerSignUp.jsp">Click here</a></h2>
<!-- user register form -->
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

    <button type="submit">Sign Up</button>
</form>

</body>
</html>