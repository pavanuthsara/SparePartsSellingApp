<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>All Products</title>
    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
    <div class="container mx-auto p-6">
        <h1 class="text-3xl font-bold text-center mb-8">All Spare Parts</h1>
        
        <!-- Link to add new product -->
        <div class="mb-6">
            <a href="addProduct.jsp" class="inline-block bg-blue-600 text-white py-2 px-4 rounded hover:bg-blue-700">
                Add New Product
            </a>
        </div>

        <!-- Products Grid -->
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
            <c:forEach var="sparePart" items="${spareParts}">
                <div class="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-lg transition-shadow duration-300">
                    <!-- Images Carousel (display first image or placeholder) -->
                    <div class="relative h-48">
                        <c:choose>
                            <c:when test="${not empty sparePart.imageUrls}">
                                <img src="${pageContext.request.contextPath}/${sparePart.imageUrls[0]}" 
                                     alt="${sparePart.title}" 
                                     class="w-full h-full object-cover">
                            </c:when>
                            <c:otherwise>
                                <div class="w-full h-full bg-gray-200 flex items-center justify-center">
                                    <span class="text-gray-500">No Image</span>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    
                    <!-- Product Details -->
                    <div class="p-4">
                        <h2 class="text-xl font-semibold text-gray-800">${sparePart.title}</h2>
                        <p class="text-gray-600 mt-1"><strong>Quantity:</strong> ${sparePart.quantity}</p>
                        <p class="text-gray-600"><strong>Unit Price:</strong> $${sparePart.unitPrice}</p>
                        <p class="text-gray-600"><strong>Location:</strong> ${sparePart.location}</p>
                        <p class="text-gray-600"><strong>Status:</strong> ${sparePart.status}</p>
                        <p class="text-gray-600 mt-2 line-clamp-3"><strong>Description:</strong> ${sparePart.description}</p>
                        
                        <!-- Additional Images (if any) -->
                        <c:if test="${sparePart.imageUrls.size() > 1}">
                            <div class="mt-4">
                                <p class="text-sm text-gray-500">Additional Images:</p>
                                <div class="flex space-x-2 overflow-x-auto">
                                    <c:forEach var="imageUrl" items="${sparePart.imageUrls}" begin="1">
                                        <img src="${pageContext.request.contextPath}/${imageUrl}" 
                                             alt="Additional Image" 
                                             class="w-16 h-16 object-cover rounded">
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Empty State -->
        <c:if test="${empty spareParts}">
            <div class="text-center text-gray-600 mt-8">
                <p>No products found. <a href="addProduct.jsp" class="text-blue-600 hover:underline">Add a product</a> to get started.</p>
            </div>
        </c:if>
    </div>
</body>
</html>