package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.SparePart;
import services.SparePartServices;

@WebServlet("/EditProduct")
public class EditProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditProduct() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Edit product servlet called");

        int productId = Integer.parseInt(request.getParameter("productId"));
        String title = request.getParameter("title");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String sellerEmail = (String) request.getSession().getAttribute("sellerEmail");

        SparePart updatedProduct = new SparePart(productId, title, quantity, unitPrice, location, description, status, null, sellerEmail);

        SparePartServices.updateSparePart(updatedProduct);

        response.sendRedirect("GetSellerProducts");
    }
}