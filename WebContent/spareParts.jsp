<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String buyerEmail = (String)session.getAttribute("buyerEmail");
    
    // Check if session exists
    if (session == null || buyerEmail == null) {
%>
        <script>
            // Redirect to userSignIn.jsp and refresh
            window.location.href = "userSignIn.jsp?t=" + new Date().getTime();
        </script>
<%
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Spare Parts Catalog</title>
    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="bg-gray-100 flex">
    <!-- Sidebar -->
    <aside id="sidebar" class="w-64 h-screen bg-gray-800 text-white fixed top-0 left-0 flex flex-col transition-transform duration-300 md:translate-x-0 -translate-x-full">
        <!-- Sidebar Header -->
        <div class="p-4 border-b border-gray-700">
            <h2 class="text-2xl font-semibold">Buyer Dashboard</h2>
        </div>
        <!-- Navigation Links -->
        <nav class="flex-1 p-4">
            <ul class="space-y-2">
                <li>
                    <a href="buyerDashboard.jsp" class="flex items-center p-2 rounded-lg hover:bg-gray-700">
                        <i class="fas fa-tachometer-alt mr-3"></i>
                        <span>Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="GetAllProducts" class="flex items-center p-2 rounded-lg hover:bg-gray-700">
                        <i class="fas fa-store mr-3"></i>
                        <span>Browse Products</span>
                    </a>
                </li>
                <li>
                    <a href="myOrders.jsp" class="flex items-center p-2 rounded-lg hover:bg-gray-700">
                        <i class="fas fa-shopping-cart mr-3"></i>
                        <span>My Orders</span>
                    </a>
                </li>
                <li>
                    <a href="GetAllProducts" class="flex items-center p-2 rounded-lg hover:bg-gray-700">
                        <i class="fas fa-user mr-3"></i>
                        <span>Profile</span>
                    </a>
                </li>
            </ul>
        </nav>
        <!-- Logout Button -->
        <div class="p-4 border-t border-gray-700">
            <form action="Logout" method="post">
                <button type="submit" class="w-full flex items-center p-2 rounded-lg hover:bg-gray-700">
                    <i class="fas fa-sign-out-alt mr-3"></i>
                    <span>Logout</span>
                </button>
            </form>
        </div>
    </aside>

    <!-- Main Content -->
    <main class="flex-1 p-6 md:ml-64">
        <!-- Mobile Menu Toggle Button -->
        <button id="toggleSidebar" class="md:hidden p-2 bg-gray-800 text-white rounded mb-4">
            <i class="fas fa-bars"></i>
        </button>
        <div class="container mx-auto px-4 py-8">
            <h1 class="text-3xl font-bold text-center mb-8">Spare Parts Catalog</h1>
            <h3>Cart items : ${noOfCartItems}</h3> 
            <a href="CartCheckout">Proceed to cehckout</a>
            <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
                <c:forEach var="product" items="${products}">
                    <div class="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-lg transition-shadow duration-300">
                        <img src="${pageContext.request.contextPath}/images/${product.imagePath}" alt="${product.title}" class="w-full h-48 object-cover" onerror="this.src='https://via.placeholder.com/150';">
                        <div class="p-4">
                            <h2 class="text-xl font-semibold mb-2">${product.title}</h2>
                            <p class="text-gray-600 mb-2">Product ID : ${product.id}</p>
                            <p class="text-gray-600 mb-2">${product.description}</p>
                            <p class="text-lg font-bold text-orange-600 mb-2">${product.unitPrice} LKR</p>
                            <p class="text-sm text-gray-500 mb-1">Quantity: ${product.quantity}</p>
                            <p class="text-sm text-gray-500 mb-1">Location: ${product.location}</p>
                            <p class="text-sm text-gray-500 mb-1">Status: ${product.status}</p>
                            <p class="text-sm text-gray-500">Seller: ${product.sellerEmail}</p>
                            
                            <form method="get" action="AddToCart" >
                            	<input type="hidden" value=${product.id} name="productId">
                            	<input type="hidden" value=${product.unitPrice} name="unitPrice">
                            	<input type="hidden" value=${product.title} name="title">
                            	<input type="number" placeholder="quantity" name="quantity">
                            	<button type="submit">Add to cart</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </main>

    <!-- JavaScript for Sidebar Toggle -->
    <script>
        const sidebar = document.getElementById('sidebar');
        const toggleSidebar = document.getElementById('toggleSidebar');

        toggleSidebar.addEventListener('click', () => {
            sidebar.classList.toggle('-translate-x-full');
        });
    </script>
</body>
</html>