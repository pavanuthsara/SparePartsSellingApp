<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Seller Sign Up</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center bg-[url('https://source.unsplash.com/random/1920x1080')] bg-cover bg-center">
<div class="max-w-md w-full bg-white p-8 rounded-lg shadow-lg">
    <h1 class="text-2xl font-bold text-center text-gray-800 mb-4">Seller Sign Up</h1>

    <!-- Sign up form for seller -->
    <form action="SellerSignUp" method="post" onsubmit="return validateForm()" id="signupForm" class="space-y-4">
        <div>
            <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
            <input type="text" name="email" id="email" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-yellow-500 focus:border-yellow-500">
            <div id="emailError" class="error text-red-500 text-xs mt-1 hidden">Please enter a valid email address</div>
        </div>

        <div>
            <label for="name" class="block text-sm font-medium text-gray-700">Seller Full Name</label>
            <input type="text" name="name" id="name" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-yellow-500 focus:border-yellow-500">
            <div id="nameError" class="error text-red-500 text-xs mt-1 hidden">Name should contain only letters and spaces</div>
        </div>

        <div>
            <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
            <input type="password" name="password" id="password" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-yellow-500 focus:border-yellow-500">
            <div id="passwordError" class="error text-red-500 text-xs mt-1 hidden">Password must be at least 8 characters long with uppercase, lowercase, number, and special character</div>
        </div>

        <div>
            <label for="rePassword" class="block text-sm font-medium text-gray-700">Re-enter Password</label>
            <input type="password" name="rePassword" id="rePassword" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-yellow-500 focus:border-yellow-500">
            <div id="rePasswordError" class="error text-red-500 text-xs mt-1 hidden">Passwords do not match</div>
        </div>

        <div>
            <label for="mobileNumber" class="block text-sm font-medium text-gray-700">Mobile no</label>
            <input type="text" name="mobileNumber" id="mobileNumber" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-yellow-500 focus:border-yellow-500">
            <div id="mobileError" class="error text-red-500 text-xs mt-1 hidden">Please enter a valid 10-digit mobile number</div>
        </div>

        <div>
            <label for="storeName" class="block text-sm font-medium text-gray-700">Store name</label>
            <input type="text" name="storeName" id="storeName" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-yellow-500 focus:border-yellow-500">
            <div id="storeNameError" class="error text-red-500 text-xs mt-1 hidden">Store name cannot be empty and should contain only letters, numbers, and spaces</div>
        </div>

        <div class="flex items-center">
            <input type="checkbox" name="conditions" id="conditions" value="accepted" class="h-4 w-4 text-yellow-600 focus:ring-yellow-500 border-gray-300 rounded">
            <label for="conditions" class="ml-2 block text-sm text-gray-700">I have accepted User terms and conditions</label>
        </div>
        <div id="conditionsError" class="error text-red-500 text-xs mt-1 hidden">You must accept the terms and conditions</div>

        <button type="submit" class="w-full bg-yellow-500 text-white py-2 px-4 rounded-md hover:bg-yellow-600 focus:outline-none focus:ring-2 focus:ring-yellow-500 focus:ring-offset-2">Sign Up</button>
    </form>
</div>

<script>
function validateForm() {
    let isValid = true;
    
    // Reset error messages
    document.querySelectorAll('.error').forEach(error => error.classList.add('hidden'));
    
    // Email validation
    const email = document.getElementById('email').value;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        document.getElementById('emailError').classList.remove('hidden');
        isValid = false;
    }
    
    // Name validation
    const name = document.getElementById('name').value;
    const nameRegex = /^[a-zA-Z\s]+$/;
    if (!nameRegex.test(name) || name.trim() === '') {
        document.getElementById('nameError').classList.remove('hidden');
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
    
    // Mobile number validation
    const mobile = document.getElementById('mobileNumber').value;
    const mobileRegex = /^\d{10}$/;
    if (!mobileRegex.test(mobile)) {
        document.getElementById('mobileError').classList.remove('hidden');
        isValid = false;
    }
    
    // Store name validation
    const storeName = document.getElementById('storeName').value;
    const storeNameRegex = /^[a-zA-Z0-9\s]+$/;
    if (!storeNameRegex.test(storeName) || storeName.trim() === '') {
        document.getElementById('storeNameError').classList.remove('hidden');
        isValid = false;
    }
    
    // Terms and conditions validation
    const conditions = document.getElementById('conditions').checked;
    if (!conditions) {
        document.getElementById('conditionsError').classList.remove('hidden');
        isValid = false;
    }
    
    return isValid;
}
</script>

</body>
</html>