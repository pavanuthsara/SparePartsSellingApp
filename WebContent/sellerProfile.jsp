<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.Seller" %>

<%
    // Check if session exists
    String sellerEmail = (String) session.getAttribute("sellerEmail");
    if (session == null || sellerEmail == null) {
%>
        <script>
            window.location.href = "sellerSignIn.jsp?t=" + new Date().getTime();
        </script>
<%
        return;
    }

    // Get seller object from request
    Seller seller = (Seller) request.getAttribute("seller");
    if (seller == null) {
%>
        <script>
            alert("Unable to fetch profile details!");
            window.location.href = "sellerDashboard.jsp";
        </script>
<%
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Seller Profile</title>
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
                    <a href="addProduct.jsp" class="flex items-center p-2 rounded-lg hover:bg-gray-7
                    00">
                        <i class="fas fa-box mr-3"></i>
                        <span>Add Product</span>
                    </a>
                </li>
                <li>
                    <a href="GetBuyerOrders" class="flex items-center p-2 rounded-lg hover:bg-gray-700">
                        <i class="fas fa-shopping-cart mr-3"></i>
                        <span>Orders</span>
                    </a>
                </li>
                <li>
                    <a href="SellerProfile" class="flex items-center p-2 rounded-lg bg-gray-700">
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
        <h1 class="text-3xl font-bold mb-4">Seller Profile</h1>
        <div class="bg-white p-6 rounded-lg shadow-md">
            <h2 class="text-2xl font-semibold mb-4">Profile Details</h2>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                    <label class="block text-gray-700 font-medium">Name</label>
                    <p class="text-gray-900"><%= seller.getName() %></p>
                </div>
                <div>
                    <label class="block text-gray-700 font-medium">Email</label>
                    <p class="text-gray-900"><%= seller.getEmail() %></p>
                </div>
                <div>
                    <label class="block text-gray-700 font-medium">Mobile Number</label>
                    <p class="text-gray-900"><%= seller.getMobileNumber() %></p>
                </div>
                <div>
                    <label class="block text-gray-700 font-medium">Store Name</label>
                    <p class="text-gray-900"><%= seller.getStoreName() %></p>
                </div>
            </div>
            <div class="mt-6 flex space-x-4">
                <a href="EditSellerProfile" class="bg-yellow-600 text-white px-4 py-2 rounded hover:bg-yellow-700">
                    <i class="fas fa-edit mr-2"></i>Edit Details
                </a>
                <form action="SellerDeleteProfile" method="post" onsubmit="return confirm('Are you sure you want to delete your profile? This action cannot be undone.');">
                    <button type="submit" class="bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700">
                        <i class="fas fa-trash-alt mr-2"></i>Delete Profile
                    </button>
                </form>
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