<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.SparePart, services.SparePartServices" %>
<%
    String sellerEmail = (String)session.getAttribute("sellerEmail");
    if (session == null || sellerEmail == null) {
%>
        <script>
            window.location.href = "sellerSignIn.jsp?t=" + new Date().getTime();
        </script>
<%
        return;
    }

    int productId = Integer.parseInt(request.getParameter("id"));
    SparePart product = null;
    for (SparePart p : SparePartServices.getSellerProduct(sellerEmail)) {
        if (p.getId() == productId) {
            product = p;
            break;
        }
    }
    if (product == null) {
%>
        <script>
            alert('Product not found');
            window.location.href = 'sellerDashboard.jsp';
        </script>
<%
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Product</title>
<script src="https://cdn.tailwindcss.com"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="bg-gray-100">
    <div class="container mx-auto p-6">
        <h1 class="text-3xl font-bold mb-6">Edit Product</h1>
        <form action="EditProduct" method="post" class="bg-white p-6 rounded-lg shadow-md">
            <input type="hidden" name="productId" value="<%= product.getId() %>">
            <div class="mb-4">
                <label class="block text-gray-700">Title</label>
                <input type="text" name="title" value="<%= product.getTitle() %>" class="w-full p-2 border rounded" required>
            </div>
            <div class="mb-4">
                <label class="block text-gray-700">Quantity</label>
                <input type="number" name="quantity" value="<%= product.getQuantity() %>" class="w-full p-2 border rounded" required>
            </div>
            <div class="mb-4">
                <label class="block text-gray-700">Unit Price</label>
                <input type="number" step="0.01" name="unitPrice" value="<%= product.getUnitPrice() %>" class="w-full p-2 border rounded" required>
            </div>
            <div class="mb-4">
                <label class="block text-gray-700">Location</label>
                <input type="text" name="location" value="<%= product.getLocation() %>" class="w-full p-2 border rounded" required>
            </div>
            <div class="mb-4">
                <label class="block text-gray-700">Description</label>
                <textarea name="description" class="w-full p-2 border rounded" required><%= product.getDescription() %></textarea>
            </div>
            <div class="mb-4">
                <label class="block text-gray-700">Status</label>
                <select name="status" class="w-full p-2 border rounded" required>
                    <option value="Available" <%= product.getStatus().equals("Available") ? "selected" : "" %>>Available</option>
                    <option value="Out of Stock" <%= product.getStatus().equals("Out of Stock") ? "selected" : "" %>>Out of Stock</option>
                </select>
            </div>
            <div class="flex space-x-4">
                <button type="submit" class="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600">
                    <i class="fas fa-save mr-2"></i>Save Changes
                </button>
                <a href="sellerDashboard.jsp" class="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600">
                    <i class="fas fa-arrow-left mr-2"></i>Cancel
                </a>
            </div>
        </form>
    </div>
</body>
</html>