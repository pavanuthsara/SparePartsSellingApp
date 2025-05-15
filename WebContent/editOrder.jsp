<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.Order" %>

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

    Order order = (Order) request.getAttribute("order");
    if (order == null) {
%>
        <script>
            alert("Unable to fetch order details!");
            window.location.href = "GetBuyerOrders";
        </script>
<%
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Edit Order</title>
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
        <h1 class="text-3xl font-bold mb-6">Edit Order #<%= order.getOrderId() %></h1>
        <div class="bg-white p-6 rounded-lg shadow-md">
            <h2 class="text-2xl font-semibold mb-4">Update Order Details</h2>
            <form action="EditOrder" method="post" onsubmit="return validateForm()">
                <input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
                <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                    <div>
                        <label for="shippingAddress" class="block text-gray-700 font-medium">Shipping Address</label>
                        <input type="text" id="shippingAddress" name="shippingAddress" value="<%= order.getShippingAddress() %>" class="w-full p-2 border rounded focus:outline-none focus:ring-2 focus:ring-yellow-500" required>
                        <p id="shippingAddressError" class="text-red-500 text-sm mt-1 hidden">Shipping address must be between 5 and 100 characters.</p>
                    </div>
                    <div>
                        <label for="specialNote" class="block text-gray-700 font-medium">Special Note (Optional)</label>
                        <input type="text" id="specialNote" name="specialNote" value="<%= order.getSpecialNote() %>" class="w-full p-2 border rounded focus:outline-none focus:ring-2 focus:ring-yellow-500">
                        <p id="specialNoteError" class="text-red-500 text-sm mt-1 hidden">Special note cannot exceed 200 characters.</p>
                    </div>
                    <div>
                        <label for="mobileNumber" class="block text-gray-700 font-medium">Mobile Number</label>
                        <input type="text" id="mobileNumber" name="mobileNumber" value="<%= order.getMobileNumber() %>" class="w-full p-2 border rounded focus:outline-none focus:ring-2 focus:ring-yellow-500" required>
                        <p id="mobileNumberError" class="text-red-500 text-sm mt-1 hidden">Please enter a valid phone number (e.g., +94 followed by 9 digits or 10 digits).</p>
                    </div>
                    <div>
                        <label for="paymentMethod" class="block text-gray-700 font-medium">Payment Method</label>
                        <select id="paymentMethod" name="paymentMethod" class="w-full p-2 border rounded focus:outline-none focus:ring-2 focus:ring-yellow-500" required>
                            <option value="cashOnDelivery" <%= order.getPaymentMethod().equals("cashOnDelivery") ? "selected" : "" %>>Cash On Delivery</option>
                            <option value="creditCard" <%= order.getPaymentMethod().equals("creditCard") ? "selected" : "" %>>Credit Card</option>
                            <option value="debitCard" <%= order.getPaymentMethod().equals("debitCard") ? "selected" : "" %>>Debit Card</option>
                        </select>
                        <p id="paymentMethodError" class="text-red-500 text-sm mt-1 hidden">Please select a payment method.</p>
                    </div>
                    <div>
                        <label class="block text-gray-700 font-medium">Fast Delivery</label>
                        <label class="inline-flex items-center mt-2">
                            <input type="checkbox" name="fastDelivery" class="h-5 w-5 text-yellow-500 focus:ring-yellow-500 border-gray-300 rounded" <%= order.isFastDelivery() ? "checked" : "" %>>
                            <span class="ml-2 text-gray-700">Fast Delivery (Extra 500 LKR)</span>
                        </label>
                    </div>
                </div>
                <div class="mt-6 flex justify-end space-x-4">
                    <button type="submit" class="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600 flex items-center">
                        <i class="fas fa-save mr-2"></i>Save Changes
                    </button>
                    <a href="GetBuyerOrders" class="bg-gray-600 text-white px-4 py-2 rounded hover:bg-gray-700 flex items-center">
                        <i class="fas fa-times mr-2"></i>Cancel
                    </a>
                </div>
            </form>
        </div>
    </main>

    <!-- JavaScript for Sidebar Toggle and Form Validation -->
    <script>
        const sidebar = document.getElementById("sidebar");
        const toggleSidebar = document.getElementById("toggleSidebar");

        toggleSidebar.addEventListener("click", () => {
            sidebar.classList.toggle("-translate-x-full");
        });

        function validateForm() {
            let isValid = true;

            // Reset error messages
            document.querySelectorAll('.text-red-500').forEach(el => el.classList.add('hidden'));

            // Shipping Address validation
            const shippingAddress = document.getElementById('shippingAddress').value.trim();
            if (shippingAddress.length < 5 || shippingAddress.length > 100) {
                document.getElementById('shippingAddressError').classList.remove('hidden');
                isValid = false;
            }

            // Special Note validation
            const specialNote = document.getElementById('specialNote').value.trim();
            if (specialNote.length > 200) {
                document.getElementById('specialNoteError').classList.remove('hidden');
                isValid = false;
            }

            // Mobile Number validation
            const mobileNumber = document.getElementById('mobileNumber').value.trim();
            const phonePattern = /^(\+94\d{9}|\d{10})$/;
            if (!phonePattern.test(mobileNumber)) {
                document.getElementById('mobileNumberError').classList.remove('hidden');
                isValid = false;
            }

            // Payment Method validation
            const paymentMethod = document.getElementById('paymentMethod').value;
            if (!paymentMethod) {
                document.getElementById('paymentMethodError').classList.remove('hidden');
                isValid = false;
            }

            return isValid;
        }
    </script>
</body>
</html>