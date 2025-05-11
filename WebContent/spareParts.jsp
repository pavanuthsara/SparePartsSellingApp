<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Spare Parts Catalog</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
    <div class="container mx-auto px-4 py-8">
        <h1 class="text-3xl font-bold text-center mb-8">Spare Parts Catalog</h1>
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
            <c:forEach var="product" items="${products}">
                <div class="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-lg transition-shadow duration-300">
                    <img src="${pageContext.request.contextPath}/images/${product.imagePath}" alt="${product.title}" class="w-full h-48 object-cover" onerror="this.src='https://via.placeholder.com/150';">
                    <div class="p-4">
                        <h2 class="text-xl font-semibold mb-2">${product.title}</h2>
                        <p class="text-gray-600 mb-2">${product.description}</p>
                        <p class="text-lg font-bold text-green-600 mb-2">$${product.unitPrice}</p>
                        <p class="text-sm text-gray-500 mb-1">Quantity: ${product.quantity}</p>
                        <p class="text-sm text-gray-500 mb-1">Location: ${product.location}</p>
                        <p class="text-sm text-gray-500 mb-1">Status: ${product.status}</p>
                        <p class="text-sm text-gray-500">Seller: ${product.sellerEmail}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>