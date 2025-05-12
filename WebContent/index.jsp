<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Spare Parts Marketplace</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 font-sans">
    <!-- Header -->
    <header class="bg-white shadow">
        <nav class="container mx-auto px-4 py-4 flex justify-between items-center">
            <div class="text-2xl font-bold text-orange-600">SpareParts.lk</div>
            <div class="flex items-center space-x-4">
                <a href="index.jsp" class="text-gray-600 hover:text-blue-600">Home</a>
                <a href="userSignUp.jsp" class="text-gray-600 hover:text-blue-600">Buy</a>
                <a href="sellerSignUp.jsp" class="text-gray-600 hover:text-blue-600">Sell</a>
                <input type="text" placeholder="Search parts..." class="border rounded px-2 py-1">
                <a href="userSignIn.jsp" class="bg-orange-600 text-white px-4 py-2 rounded hover:bg-orange-700">Login</a>
                <a href="userSignUp.jsp" class="bg-orange-600 text-white px-4 py-2 rounded hover:bg-orange-700">Signup</a>
            </div>
        </nav>
    </header>

    <!-- Hero Section -->
    <section class="bg-yellow-500 text-white py-20">
        <div class="container mx-auto px-4 text-center">
            <h1 class="text-4xl md:text-5xl font-bold mb-4">Your Marketplace for Spare Parts</h1>
            <p class="text-lg mb-8">Buy quality parts or sell your spares with ease.</p>
            <div class="flex justify-center space-x-4">
                <a href="#" class="bg-white text-orange-600 px-6 py-3 rounded font-semibold hover:bg-gray-100">Browse Parts</a>
                <a href="sellerSignUp.jsp" class="border-2 border-black text-black px-6 py-3 rounded font-semibold hover:bg-white hover:text-blue-600">Start Selling</a>
            </div>
        </div>
    </section>

    <!-- Featured Listings -->
    <section class="py-16">
        <div class="container mx-auto px-4">
            <h2 class="text-3xl font-bold text-center mb-12">Featured Spare Parts</h2>
            <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
                <div class="bg-white shadow rounded-lg p-4">
                    <img src="https://via.placeholder.com/150" alt="Car Battery" class="w-full h-40 object-cover rounded">
                    <h3 class="text-xl font-semibold mt-4">Car Battery</h3>
                    <p class="text-gray-600">$50.00</p>
                    <a href="#" class="mt-4 inline-block bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">View Details</a>
                </div>
                <div class="bg-white shadow rounded-lg p-4">
                    <img src="https://via.placeholder.com/150" alt="Brake Pads" class="w-full h-40 object-cover rounded">
                    <h3 class="text-xl font-semibold mt-4">Brake Pads</h3>
                    <p class="text-gray-600">$30.00</p>
                    <a href="#" class="mt-4 inline-block bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">View Details</a>
                </div>
                <div class="bg-white shadow rounded-lg p-4">
                    <img src="https://via.placeholder.com/150" alt="Oil Filter" class="w-full h-40 object-cover rounded">
                    <h3 class="text-xl font-semibold mt-4">Oil Filter</h3>
                    <p class="text-gray-600">$15.00</p>
                    <a href="#" class="mt-4 inline-block bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">View Details</a>
                </div>
            </div>
        </div>
    </section>

    <!-- How It Works -->
    <section class="bg-gray-200 py-16">
        <div class="container mx-auto px-4">
            <h2 class="text-3xl font-bold text-center mb-12">How It Works</h2>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
                <div class="text-center">
                    <h3 class="text-2xl font-semibold mb-4">For Buyers</h3>
                    <p class="text-gray-600 mb-4">Search for spare parts, compare prices, and buy with confidence from trusted sellers.</p>
                    <a href="#" class="text-blue-600 font-semibold hover:underline">Learn More</a>
                </div>
                <div class="text-center">
                    <h3 class="text-2xl font-semibold mb-4">For Sellers</h3>
                    <p class="text-gray-600 mb-4">List your spare parts in minutes and reach thousands of buyers nationwide.</p>
                    <a href="#" class="text-blue-600 font-semibold hover:underline">Learn More</a>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer class="bg-gray-800 text-white py-8">
        <div class="container mx-auto px-4 text-center">
            <div class="flex justify-center space-x-4 mb-4">
                <a href="#" class="hover:text-blue-400">About</a>
                <a href="#" class="hover:text-blue-400">Contact</a>
                <a href="#" class="hover:text-blue-400">Terms</a>
                <a href="#" class="hover:text-blue-400">Privacy</a>
            </div>
            <p>&copy; 2025 SpareParts. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>