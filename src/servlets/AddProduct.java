package servlets;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.SparePart;
import services.SparePartServices;

@WebServlet("/AddProduct")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,     // 10MB
        maxRequestSize = 1024 * 1024 * 50   // 50MB
)
public class AddProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddProduct() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Add product servlet called");

        // Get form fields
        String title = request.getParameter("title");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        // List to store image binary data
        List<byte[]> images = new ArrayList<>();

        // Process uploaded files
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            if (fileName != null && !fileName.isEmpty()) {
                // Read the file content into a byte array
                InputStream inputStream = part.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                images.add(outputStream.toByteArray());
                inputStream.close();
                outputStream.close();
            }
        }

        // Create SparePart object with image binary data
        SparePart sparePart = new SparePart(title, quantity, unitPrice, location, description, status, images);
        List<Integer> imageIds = SparePartServices.addSparePart(sparePart);

        // Convert image IDs to URLs for ImageServlet
        List<String> imageUrls = new ArrayList<>();
        String contextPath = request.getContextPath();
        for (Integer imageId : imageIds) {
            imageUrls.add(contextPath + "/ImageServlet?id=" + imageId);
        }

        // Set imageUrls in SparePart for consistency
        sparePart.setImageUrls(imageUrls);

        // Store data in request attributes for display
        request.setAttribute("title", title);
        request.setAttribute("quantity", quantity);
        request.setAttribute("unitPrice", unitPrice);
        request.setAttribute("location", location);
        request.setAttribute("description", description);
        request.setAttribute("status", status);
        request.setAttribute("imagePaths", imageUrls); // For productResult.jsp

        // Forward to result page
        RequestDispatcher dispatcher = request.getRequestDispatcher("productResult.jsp");
        dispatcher.forward(request, response);
    }

    // Extract file name from Part
    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return null;
    }
}