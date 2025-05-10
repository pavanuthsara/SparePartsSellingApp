<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    	String buyerEmail = (String)session.getAttribute("buyerEmail");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Buyer Dashboard</h1>
<p>Buyer email</p> <%= buyerEmail %>

<form action="Logout" method="post">
	<input type="submit" value="logout">
</form>
</body>
</html>