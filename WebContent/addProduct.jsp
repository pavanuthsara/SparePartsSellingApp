<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sell product</title>
</head>
<body>

<h1>List your spare part here</h1>

<form action="AddProduct" method="post" enctype="multipart/form-data">

    <label for="">Title</label>
    <input type="text" name="title">

    <label for="">Quantity</label>
    <input type="number" name="quantity">

    <label for="">Unit price</label>
    <input type="number" name="unitPrice">

    <label for="">Location</label>
    <input type="text" name="location">

    <label for="">Description</label>
    <textarea name="description" id="" cols="30" rows="5"></textarea>

    <!-- image upload input in here -->
    <label>Upload Images:</label>
    <input type="file" name="images" multiple accept="image/*"><br><br>
        
    <label for=""></label>
    <select name="status" id="">
        <option value="used">Used</option>
        <option value="brandNew">Brand New</option>
    </select>

    <button type="submit">Submit Spare Part</button>
</form>

</body>
</html>