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

@MultipartConfig
@WebServlet("/AddProduct")
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
		String image = request.getParameter("image");
		String sellerEmail = request.getParameter("sellerEmail");
		
		Part file = request.getPart("image");
		String imageFileName = file.getSubmittedFileName();
        System.out.println("selected file name : " + imageFileName);
        
        String uploadPath = "C:/Users/pavan/eclipse-webapp/SparePartsSellingApp/WebContent/images/" + imageFileName;
        System.out.println("upload file path : " + uploadPath);
        
        FileOutputStream fos = new FileOutputStream(uploadPath);
        InputStream is = file.getInputStream();
        
        byte[] data = new byte[is.available()];
        is.read(data);
        fos.write(data);
        fos.close();
        
        SparePart sparePart = new SparePart(title, quantity, unitPrice, location, description, status, imageFileName, sellerEmail);
        SparePartServices.addSparePart(sparePart);
        
        // Store data in request attributes for display
        request.setAttribute("title", title);
        request.setAttribute("quantity", quantity);
        request.setAttribute("unitPrice", unitPrice);
        request.setAttribute("location", location);
        request.setAttribute("description", description);
        request.setAttribute("status", status);
        request.setAttribute("image", image);
        request.setAttribute("sellerEmail", sellerEmail);

        // Forward to a result page
//        RequestDispatcher dispatcher = request.getRequestDispatcher("productResult.jsp");
//        dispatcher.forward(request, response);
		
	}

}
