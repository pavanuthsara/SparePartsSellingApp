<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.Buyer" %>

<%
    // Check if session exists
    String buyerEmail = (String) session.getAttribute("buyerEmail");
    if (session == null || buyerEmail == null) {
%>
        <script>
            window.location.href = "userSignIn.jsp?t=" + new Date().getTime();
        </script>
<%
        return;
    }

    // Get buyer object from request
    Buyer buyer = (Buyer) request.getAttribute("buyer");
    if (buyer == null) {
%>
        <script>
            alert("Unable to fetch profile details!");
            window.location.href = "buyerDashboard.jsp";
        </script>
<%
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile</title>
<!-- Tailwind CSS CDN -->
<script src="https://cdn.tailwindcss.com"></script>
<!-- Font Awesome for icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="bg-gray-100 flex">
    <!-- Sidebar -->
    <aside id="sidebar" class="w-64 h-screen bg-gray-800 text-white fixed top-0 left-0 flex flex-col transition-transform duration-300 md:translate-x-0 -translate-x-full">
        <div class="p-4 border-b border-gray-700">
            <h2 class="text-2xl font-semibold">Buyer Dashboard</h2>
        </div>
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
                    <a href="BuyerProfile" class="flex items-center p-2 rounded-lg bg-gray-700">
                        <i class="fas fa-user mr-3"></i>
                        <span>Profile</span>
                    </a>
                </li>
            </ul>
        </nav>
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
        <h1 class="text-3xl font-bold mb-4">Edit Profile</h1>
        <div class="bg-white p-6 rounded-lg shadow-md">
            <h2 class="text-2xl font-semibold mb-4">Update Your Details</h2>
            <form action="BuyerEditProfile" method="post">
                <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                    <div>
                        <label class="block text-gray-700 font-medium" for="name">Name</label>
                        <input type="text" id="name" name="name" value="<%= buyer.getName() %>" class="w-full p-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-600" required>
                    </div>
                    <div>
                        <label class="block text-gray-700 font-medium" for="email">Email (Read-only)</label>
                        <input type="email" id="email" name="email" value="<%= buyer.getEmail() %>" class="w-full p-2 border rounded bg-gray-100" readonly>
                    </div>
                    <div>
                        <label class="block text-gray-700 font-medium" for="mobileNumber">Mobile Number</label>
                        <input type="text" id="mobileNumber" name="mobileNumber" value="<%= buyer.getMobileNumber() %>" class="w-full p-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-600" required>
                    </div>
                    <div>
                        <label class="block text-gray-700 font-medium" for="shippingAddress">Shipping Address</label>
                        <input type="text" id="shippingAddress" name="shippingAddress" value="<%= buyer.getShippingAddress() %>" class="w-full p-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-600" required>
                    </div>
                </div>
                <div class="mt-6 flex space-x-4">
                    <button type="submit" class="bg-yellow-600 text-white px-4 py-2 rounded hover:bg-yellow-700">
                        <i class="fas fa-save mr-2"></i>Save Changes
                    </button>
                    <a href="BuyerProfile" class="bg-gray-600 text-white px-4 py-2 rounded hover:bg-gray-700">
                        <i class="fas fa-times mr-2"></i>Cancel
                    </a>
                </div>
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