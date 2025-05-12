<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList, models.SparePart" %>

<%
    String sellerEmail = (String)session.getAttribute("sellerEmail");
    
    // Check if session exists
    if (session == null || sellerEmail == null) {
%>
        <script>
            // Redirect to sellerSignIn.jsp and refresh
            window.location.href = "sellerSignIn.jsp?t=" + new Date().getTime();
        </script>
<%
        return;
    }
    
    // Get products from request attribute
    ArrayList<SparePart> products = (ArrayList<SparePart>) request.getAttribute("products");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Seller Dashboard</title>
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
            <h2 class="text-2xl font-semibold">Seller Dashboard</h2>
        </div>
        <!-- Navigation Links -->
        <nav class="flex-1 p-4">
            <ul class="space-y-2">
                <li>
                    <a href="sellerDashboard.jsp" class="flex items-center p-2 rounded-lg hover:bg-gray-700">
                        <i class="fas fa-tachometer-alt mr-3"></i>
                        <span>Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="addProduct.jsp" class="flex items-center p-2 rounded-lg hover:bg-gray-700">
                        <i class="fas fa-box mr-3"></i>
                        <span>Add Product</span>
                    </a>
                </li>
                <li>
                    <a href="orders.jsp" class="flex items-center p-2 rounded-lg hover:bg-gray-700">
                        <i class="fas fa-shopping-cart mr-3"></i>
                        <span>Orders</span>
                    </a>
                </li>
                <li>
                    <a href="SellerProfile" class="flex items-center p-2 rounded-lg hover:bg-gray-700">
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
        <h1 class="text-3xl font-bold mb-4">Welcome, <%= sellerEmail %></h1>
        
        <!-- Products Section -->
        <div class="mt-6">
            <h2 class="text-2xl font-semibold mb-4">Your Products</h2>
            <% if (products == null || products.isEmpty()) { %>
                <p class="text-gray-700">No products found. Add products using the "Add Product" option.</p>
            <% } else { %>
                <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                    <% for (SparePart product : products) { %>
                        <div class="bg-white p-6 rounded-lg shadow-md">
                            <img src="<%= product.getImagePath() %>" alt="<%= product.getTitle() %>" class="w-full h-48 object-cover rounded-md mb-4">
                            <h3 class="text-xl font-semibold"><%= product.getTitle() %></h3>
                            <p class="text-gray-600">Price: $<%= product.getUnitPrice() %></p>
                            <p class="text-gray-600">Quantity: <%= product.getQuantity() %></p>
                            <p class="text-gray-600">Location: <%= product.getLocation() %></p>
                            <p class="text-gray-600">Status: <%= product.getStatus() %></p>
                            <div class="mt-4 flex space-x-2">
                                <a href="editProduct.jsp?id=<%= product.getId() %>" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
                                    <i class="fas fa-edit mr-2"></i>Edit
                                </a>
                                <form action="DeleteProduct" method="post">
                                    <input type="hidden" name="productId" value="<%= product.getId() %>">
                                    <button type="submit" class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600" onclick="return confirm('Are you sure you want to delete this product?')">
                                        <i class="fas fa-trash mr-2"></i>Delete
                                    </button>
                                </form>
                            </div>
                        </div>
                    <% } %>
                </div>
            <% } %>
        </div>
    </main>

    <!-- JavaScript for Sidebar Toggle -->
    <script>
        const sidebar = document.getElementById('sidebar');
        const toggleSidebar = document.getElementById('toggleSidebar');

        toggleSidebar.addEventListener('click', () => {
            sidebar.classList.toggle('-translate-x-full');
        });

        // Automatically fetch products when the page loads
        window.onload = function() {
            fetch('GetSellerProducts')
                .then(response => response.text())
                .then(data => {
                    // Page is reloaded by the servlet
                });
        };
    </script>
</body>
</html>