<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList, models.Order" %>

<%
    String buyerEmail = (String) session.getAttribute("buyerEmail");
    if (session == null || buyerEmail == null) {
%>
        <script>
            window.location.href = "userSignIn.jsp?t=" + new Date().getTime();
        </script>
<%
        return;
    }

    ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("orders");
    if (orders == null) {
%>
        <script>
            alert("Unable to fetch orders!");
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
    <title>Buyer Orders</title>
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
                    <a href="GetBuyerOrders" class="flex items-center p-2 rounded-lg bg-gray-700">
                        <i class="fas fa-shopping-cart mr-3"></i>
                        <span>My Orders</span>
                    </a>
                </li>
                <li>
                    <a href="BuyerProfile" class="flex items-center p-2 rounded-lg hover:bg-gray-700">
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
        <h1 class="text-3xl font-bold mb-6">My Orders</h1>

        <!-- Orders Table -->
        <div class="bg-white shadow-md rounded-lg overflow-hidden">
            <% if (orders.isEmpty()) { %>
                <p class="p-4 text-gray-700">No orders found.</p>
            <% } else { %>
                <table class="w-full text-left">
                    <thead class="bg-gray-800 text-white">
                        <tr>
                            <th class="p-4">Order ID</th>
                            <th class="p-4">Date</th>
                            <th class="p-4">Shipping Address</th>
                            <th class="p-4">Mobile Number</th>
                            <th class="p-4">Payment Method</th>
                            <th class="p-4">Total Cost (LKR)</th>
                            <th class="p-4">Fast Delivery</th>
                            <th class="p-4">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Order order : orders) { %>
                        <tr class="border-b">
                            <td class="p-4"><%= order.getOrderId() %></td>
                            <td class="p-4"><%= order.getDate() %></td>
                            <td class="p-4"><%= order.getShippingAddress() %></td>
                            <td class="p-4"><%= order.getMobileNumber() %></td>
                            <td class="p-4"><%= order.getPaymentMethod() %></td>
                            <td class="p-4"><%= String.format("%.2f", order.getTotalCost()) %></td>
                            <td class="p-4"><%= order.isFastDelivery() ? "Yes" : "No" %></td>
                            <td class="p-4 flex space-x-2">
                                <a href="EditOrder?orderId=<%= order.getOrderId() %>" class="bg-yellow-500 text-white px-3 py-1 rounded hover:bg-yellow-600 flex items-center">
                                    <i class="fas fa-edit mr-2"></i>Edit
                                </a>
                                <form action="DeleteOrder" method="post" onsubmit="return confirm('Are you sure you want to delete Order #<%= order.getOrderId() %>? This action cannot be undone.');">
                                    <input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
                                    <button type="submit" class="bg-red-600 text-white px-3 py-1 rounded hover:bg-red-700 flex items-center">
                                        <i class="fas fa-trash-alt mr-2"></i>Delete
                                    </button>
                                </form>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } %>
        </div>
    </main>

    <!-- JavaScript for Sidebar Toggle -->
    <script>
        const sidebar = document.getElementById("sidebar");
        const toggleSidebar = document.getElementById("toggleSidebar");

        toggleSidebar.addEventListener("click", () => {
            sidebar.classList.toggle("-translate-x-full");
        });
    </script>
</body>
</html>