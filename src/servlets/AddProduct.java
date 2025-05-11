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
	    maxFileSize = 1024 * 1024 * 10,      // 10MB
	    maxRequestSize = 1024 * 1024 * 50    // 50MB
	)
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddProduct() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Add product servlet called");
		
		// Get form fields
		String title = request.getParameter("title");
		int quantity = Integer.parseInt(request.getParameter("quantity")); 
		double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
		String location = request.getParameter("location");
		String description = request.getParameter("description");
		String status = request.getParameter("status");
		
        
//        SparePart sparePart = new SparePart(title, quantity, unitPrice, location, description, status, imagePaths);
//        SparePartServices.addSparePart(sparePart);
        
        // Store data in request attributes for display
        request.setAttribute("title", title);
        request.setAttribute("quantity", quantity);
        request.setAttribute("unitPrice", unitPrice);
        request.setAttribute("location", location);
        request.setAttribute("description", description);
        request.setAttribute("status", status);
//        request.setAttribute("imagePaths", imagePaths);

        // Forward to a result page
        RequestDispatcher dispatcher = request.getRequestDispatcher("productResult.jsp");
        dispatcher.forward(request, response);
		
	}

}
