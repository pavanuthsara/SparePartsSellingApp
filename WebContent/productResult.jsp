<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spare Part Added</title>
</head>
<body>
    <h2>Spare Part Added Successfully!</h2>
    <p><strong>Title:</strong> ${title}</p>
    <p><strong>Quantity:</strong> ${quantity}</p>
    <p><strong>Unit Price:</strong> ${unitPrice}</p>
    <p><strong>Location:</strong> ${location}</p>
    <p><strong>Description:</strong> ${description}</p>
    <p><strong>Status:</strong> ${status}</p>
    <h3>Uploaded Images:</h3>
    <c:forEach var="imagePath" items="${imagePaths}">
        <img src="/${imagePath}" alt="Spare Part Image" width="200"><br>
    </c:forEach>
    <a href="addProduct.jsp">Add Another Spare Part</a>
</body>
</html>