package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.CartItem;
import models.SparePart;
import services.SparePartServices;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Assuming you have a method to get SparePart by ID
        SparePart product = getSparePartById(productId); // Implement this method

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // Check if product already exists in cart
        boolean exists = false;
        for (CartItem item : cart) {
            if (item.getProduct().getId() == productId) {
                item.setQuantity(item.getQuantity() + quantity);
                exists = true;
                break;
            }
        }

        if (!exists) {
            cart.add(new CartItem(product, quantity));
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("GetAllProducts");
    }

    // Placeholder method - implement according to your data access layer
    private SparePart getSparePartById(int id) {
        // Implement database query to get SparePart by ID
    	SparePart sparepart = SparePartServices.getSparePartById(id);
        return sparepart;
    }
}