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
                    <a href="products.jsp" class="flex items-center p-2 rounded-lg hover:bg-gray-700">
                        <i class="fas fa-box mr-3"></i>
                        <span>Products</span>
                    </a>
                </li>
                <li>
                    <a href="orders.jsp" class="flex items-center p-2 rounded-lg hover:bg-gray-700">
                        <i class="fas fa-shopping-cart mr-3"></i>
                        <span>Orders</span>
                    </a>
                </li>
                <li>
                    <a href="profile.jsp" class="flex items-center p-2 rounded-lg hover:bg-gray-700">
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
        <p class="text-gray-700">This is your seller dashboard. Use the sidebar to navigate through your products, orders, and profile.</p>
        <!-- Add more dashboard content here -->
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