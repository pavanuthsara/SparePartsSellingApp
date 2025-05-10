<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    	String sellerEmail = (String)session.getAttribute("sellerEmail");
    	
    	//check session exists
    	if(session == null || sellerEmail == null){
    		response.sendRedirect("sellerSignIn.jsp");
    		return;
    	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Seller Dashboard</h1>
<p>Seller email</p> <%= sellerEmail %>

<form action="Logout" method="post">
	<input type="submit" value="logout">
</form>
</body>
</html>