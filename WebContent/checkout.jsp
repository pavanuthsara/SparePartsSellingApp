<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String buyerEmail = (String)session.getAttribute("buyerEmail");
    
    if (session == null || buyerEmail == null) {
%>
        <script>
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
    <title>Checkout</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="bg-gray-100 flex">
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
                    <a href="GetAllProducts" class="flex items-center p-2 rounded-lg hover:bg-gray-700">
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

    <main class="flex-1 p-6 md:ml-64">
        <button id="toggleSidebar" class="md:hidden p-2 bg-gray-800 text-white rounded mb-4">
            <i class="fas fa-bars"></i>
        </button>
        <div class="container mx-auto px-4 py-8">
            <h1 class="text-3xl font-bold mb-8">Your Cart</h1>
            <c:choose>
                <c:when test="${empty cartItems}">
                    <p class="text-gray-600">Your cart is empty.</p>
                    <a href="GetAllProducts" class="mt-4 inline-block bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">
                        Continue Shopping
                    </a>
                </c:when>
                <c:otherwise>
                    <div class="bg-white shadow-md rounded-lg overflow-hidden">
                        <table class="w-full">
                            <thead class="bg-gray-50">
                                <tr>
                                    <th class="px-6 py-3 text-left">Product</th>
                                    <th class="px-6 py-3 text-left">Price</th>
                                    <th class="px-6 py-3 text-left">Quantity</th>
                                    <th class="px-6 py-3 text-left">Total</th>
                                    <th class="px-6 py-3 text-left">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${cartItems}">
                                    <tr class="border-b">
                                        <td class="px-6 py-4">${item.product.title}</td>
                                        <td class="px-6 py-4">${item.product.unitPrice} LKR</td>
                                        <td class="px-6 py-4">
                                            <form action="UpdateCart" method="post">
                                                <input type="hidden" name="productId" value="${item.product.id}">
                                                <input type="number" name="quantity" min="1" max="${item.product.quantity}" value="${item.quantity}" class="w-20 p-1 border rounded">
                                                <button type="submit" class="ml-2 text-blue-600 hover:text-blue-800">Update</button>
                                            </form>
                                        </td>
                                        <td class="px-6 py-4">${item.product.unitPrice * item.quantity} LKR</td>
                                        <td class="px-6 py-4">
                                            <form action="RemoveFromCart" method="post">
                                                <input type="hidden" name="productId" value="${item.product.id}">
                                                <button type="submit" class="text-red-600 hover:text-red-800">Remove</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="mt-6 flex justify-between items-center">
                        <a href="GetAllProducts" class="text-blue-600 hover:text-blue-800">Continue Shopping</a>
                        <div class="text-right">
                            <p class="text-xl font-bold">Total: ${cartTotal} LKR</p>
                            <form action="PlaceOrder" method="post">
                                <button type="submit" class="mt-4 bg-green-600 text-white px-6 py-3 rounded hover:bg-green-700">
                                    Proceed to Checkout
                                </button>
                            </form>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </main>

    <script>
        const sidebar = document.getElementById('sidebar');
        const toggleSidebar = document.getElementById('toggleSidebar');

        toggleSidebar.addEventListener('click', () => {
            sidebar.classList.toggle('-translate-x-full');
        });
    </script>
</body>
</html>