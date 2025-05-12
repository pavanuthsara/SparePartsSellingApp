<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Sign In</title>
<script src="https://cdn.tailwindcss.com"></script>
<style>
    body {
        background-image: url('./img/background.webp');
        background-size: cover;
        background-position: center;
    }
</style>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
<div class="max-w-md w-full bg-white p-8 rounded-lg shadow-lg">
    <h1 class="text-2xl font-bold text-center text-gray-800 mb-4">Welcome Back!</h1>

    <h2 class="text-center text-gray-600 mb-6">Need an account? <a href="./userSignUp.jsp" class="text-yellow-700 hover:underline">Sign up here</a></h2>

    <!-- Sign in form for buyer -->
    <form action="BuyerSignIn" method="post" class="space-y-4">
        <div>
            <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
            <input type="text" name="email" id="email" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
        </div>

        <div>
            <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
            <input type="password" name="password" id="password" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
        </div>

        <button type="submit" class="w-full bg-yellow-600 text-white py-2 px-4 rounded-md hover:bg-yellow-700 focus:outline-none focus:ring-2 focus:ring-yellow-500 focus:ring-offset-2">Sign In</button>
    </form>
</div>
</body>
</html>