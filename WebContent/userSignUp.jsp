<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Sign Up</title>
<script src="https://cdn.tailwindcss.com"></script>
<style>
    body {
        background-image: url('./img/background.webp');
        background-size: cover;
        background-position: center;
    }
</style>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center bg-[url('https://source.unsplash.com/random/1920x1080')] bg-cover bg-center">
<div class="max-w-md w-full bg-white p-8 rounded-lg shadow-lg">
    <h1 class="text-2xl font-bold text-center text-gray-800 mb-4">Join us! Buy whatever you want</h1>

    <h2 class="text-center text-gray-600 mb-6">Need to become a seller? <a href="./sellerSignUp.jsp" class="text-yellow-700 hover:underline">Click here</a></h2>

    <!-- user register form -->
    <form action="BuyerSignUp" method="post" onsubmit="return validateForm()" id="signupForm" class="space-y-4">
        <div>
            <label for="name" class="block text-sm font-medium text-gray-700">Full name</label>
            <input type="text" name="name" id="name" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
            <div id="nameError" class="error text-red-500 text-xs mt-1 hidden">Name should contain only letters and spaces</div>
        </div>

        <div>
            <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
            <input type="text" name="email" id="email" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
            <div id="emailError" class="error text-red-500 text-xs mt-1 hidden">Please enter a valid email address</div>
        </div>

        <div>
            <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
            <input type="password" name="password" id="password" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
            <div id="passwordError" class="error text-red-500 text-xs mt-1 hidden">Password must be at least 8 characters long with uppercase, lowercase, number, and special character</div>
        </div>

        <div>
            <label for="rePassword" class="block text-sm font-medium text-gray-700">Re-enter Password</label>
            <input type="password" name="rePassword" id="rePassword" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
            <div id="rePasswordError" class="error text-red-500 text-xs mt-1 hidden">Passwords do not match</div>
        </div>

        <div>
            <label for="address" class="block text-sm font-medium text-gray-700">Address</label>
            <input type="text" name="address" id="address" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
            <div id="addressError" class="error text-red-500 text-xs mt-1 hidden">Address cannot be empty</div>
        </div>

        <div>
            <label for="mobileNumber" class="block text-sm font-medium text-gray-700">Mobile no</label>
            <input type="text" name="mobileNumber" id="mobileNumber" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
            <div id="mobileError" class="error text-red-500 text-xs mt-1 hidden">Please enter a valid 10-digit mobile number</div>
        </div>

        <button type="submit" class="w-full bg-yellow-600 text-white py-2 px-4 rounded-md hover:bg-yellow-700 focus:outline-none focus:ring-2 focus:ring-yellow-500 focus:ring-offset-2">Sign Up</button>
        
        <h2 class="text-center text-gray-600 mb-6">Have an account? <a href="./userSignIn.jsp" class="text-yellow-700 hover:underline">Sign in here</a></h2>
    </form>


</div>

<script>
function validateForm() {
    let isValid = true;
    
    // Reset error messages
    document.querySelectorAll('.error').forEach(error => error.classList.add('hidden'));
    
    // Name validation
    const name = document.getElementById('name').value;
    const nameRegex = /^[a-zA-Z\s]+$/;
    if (!nameRegex.test(name) || name.trim() === '') {
        document.getElementById('nameError').classList.remove('hidden');
        isValid = false;
    }
    
    // Email validation
    const email = document.getElementById('email').value;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        document.getElementById('emailError').classList.remove('hidden');
        isValid = false;
    }
    
    // Password validation
    const password = document.getElementById('password').value;
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    if (!passwordRegex.test(password)) {
        document.getElementById('passwordError').classList.remove('hidden');
        isValid = false;
    }
    
    // Password matching
    const rePassword = document.getElementById('rePassword').value;
    if (password !== rePassword) {
        document.getElementById('rePasswordError').classList.remove('hidden');
        isValid = false;
    }
    
    // Address validation
    const address = document.getElementById('address').value;
    if (address.trim() === '') {
        document.getElementById('addressError').classList.remove('hidden');
        isValid = false;
    }
    
    // Mobile number validation
    const mobile = document.getElementById('mobileNumber').value;
    const mobileRegex = /^\d{10}$/;
    if (!mobileRegex.test(mobile)) {
        document.getElementById('mobileError').classList.remove('hidden');
        isValid = false;
    }
    
    return isValid;
}
</script>

</body>
</html>