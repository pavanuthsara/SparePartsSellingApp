<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
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
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
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
                    <a href="GetSellerProducts" class="flex items-center p-2 rounded-lg hover:bg-gray-700">
                        <i class="fas fa-tachometer-alt mr-3"></i>
                        <span>Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="addProduct.jsp" class="flex items-center p-2 rounded-lg bg-gray-700">
                        <i class="fas fa-box mr-3"></i>
                        <span>Add Product</span>
                    </a>
                </li>
                <li>
                    <a href="GetSellerProducts" class="flex items-center p-2 rounded-lg hover:bg-gray-700">
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
        <div>
            <h1 class="text-3xl font-bold mb-6">List Your Spare Part</h1>
            <form action="AddProduct" method="post" enctype="multipart/form-data" class="bg-white p-6 rounded-lg shadow-md w-full">
                <div class="mb-4">
                    <label for="title" class="block text-gray-700 font-medium mb-2">Title</label>
                    <input type="text" name="title" id="title" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" required>
                </div>
                <div class="mb-4">
                    <label for="quantity" class="block text-gray-700 font-medium mb-2">Quantity</label>
                    <input type="number" name="quantity" id="quantity" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" min="1" required>
                </div>
                <div class="mb-4">
                    <label for="unitPrice" class="block text-gray-700 font-medium mb-2">Unit Price</label>
                    <input type="number" name="unitPrice" id="unitPrice" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" min="0" step="0.01" required>
                </div>
                <div class="mb-4">
                    <label for="location" class="block text-gray-700 font-medium mb-2">Location</label>
                    <input type="text" name="location" id="location" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" required>
                </div>
                <div class="mb-4">
                    <label for="description" class="block text-gray-700 font-medium mb-2">Description</label>
                    <textarea name="description" id="description" rows="5" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" required></textarea>
                </div>
                <div class="mb-4">
                    <label for="image" class="block text-gray-700 font-medium mb-2">Upload Images</label>
                    <input type="file" name="image" id="image" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" accept="image/*">
                </div>
                <div class="mb-4">
                    <label for="status" class="block text-gray-700 font-medium mb-2">Status</label>
                    <select name="status" id="status" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" required>
                        <option value="used">Used</option>
                        <option value="brandNew">Brand New</option>
                    </select>
                </div>
                <input type="hidden" name="sellerEmail" value="<%= sellerEmail %>">
                <button type="submit" class="w-full bg-yellow-500 text-black p-2 rounded-lg hover:bg-yellow-600 transition duration-300">Submit Spare Part</button>
            </form>
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