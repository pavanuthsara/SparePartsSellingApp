package servlets;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
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
    private static final String UPLOAD_DIR = "Uploads";
    // Manual file path for storing images
    private static final String UPLOAD_BASE_PATH = "C:\\Users\\pavan\\eclipse-webapp\\SparePartsSellingApp\\WebContent"; // Windows
    // For Linux/Mac, use: "/home/user/SparePartsUploads"

    public AddProduct() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Add product servlet called");

        // Get form fields
        String title = request.getParameter("title");
        String quantityStr = request.getParameter("quantity");
        String unitPriceStr = request.getParameter("unitPrice");
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        // Validate form fields
        if (title == null || title.trim().isEmpty() || quantityStr == null || unitPriceStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Required fields are missing");
            return;
        }

        int quantity;
        double unitPrice;
        try {
            quantity = Integer.parseInt(quantityStr);
            unitPrice = Double.parseDouble(unitPriceStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid quantity or unit price");
            return;
        }

        // Set the upload directory path
        String uploadFilePath = UPLOAD_BASE_PATH + File.separator + UPLOAD_DIR;

        // Create upload directory if it doesn't exist
        File uploadDir = new File(uploadFilePath);
        if (!uploadDir.exists()) {
            if (!uploadDir.mkdirs()) {
                System.err.println("Failed to create directory: " + uploadFilePath);
                throw new ServletException("Cannot create upload directory: " + uploadFilePath);
            } else {
                System.out.println("Created directory: " + uploadFilePath);
            }
        }

        // Verify directory is writable
        if (!uploadDir.canWrite()) {
            System.err.println("Directory is not writable: " + uploadFilePath);
            throw new ServletException("Upload directory is not writable: " + uploadFilePath);
        }

        // List to store image paths (relative paths for database and display)
        List<String> imagePaths = new ArrayList<>();

        // Process uploaded files
        try {
            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                if (fileName != null && !fileName.isEmpty()) {
                    String contentType = part.getContentType();
                    System.out.println("Part Name: " + part.getName() + ", Content-Type: " + contentType + ", Size: " + part.getSize());
                    if (contentType != null && contentType.startsWith("image/")) {
                        String filePath = uploadFilePath + File.separator + fileName;
                        System.out.println("Saving file to: " + filePath);
                        part.write(filePath);
                        File savedFile = new File(filePath);
                        if (savedFile.exists()) {
                            System.out.println("File successfully saved: " + filePath);
                        } else {
                            System.err.println("File not saved: " + filePath);
                        }
                        // Store relative path for database and display
                        String webPath = UPLOAD_DIR + "/" + fileName;
                        imagePaths.add(webPath);
                        System.out.println("Added image path: " + webPath);
                    } else {
                        System.out.println("Skipping non-image file: " + fileName);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing file upload: " + e.getMessage());
            throw new ServletException("File upload failed", e);
        }

        // Create SparePart object and save to database
        SparePart sparePart = new SparePart(title, quantity, unitPrice, location, description, status, imagePaths);
        try {
            SparePartServices.addSparePart(sparePart);
        } catch (Exception e) {
            System.err.println("Error saving to database: " + e.getMessage());
            throw new ServletException("Database error", e);
        }

        // Store data in request attributes for display
        request.setAttribute("title", title);
        request.setAttribute("quantity", quantity);
        request.setAttribute("unitPrice", unitPrice);
        request.setAttribute("location", location);
        request.setAttribute("description", description);
        request.setAttribute("status", status);
        request.setAttribute("imagePaths", imagePaths);

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
                String fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                return uniqueFileName;
            }
        }
        return null;
    }
}