package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Buyer;
import services.BuyerServices;

@WebServlet("/BuyerEditProfile")
public class BuyerEditProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BuyerEditProfile() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String buyerEmail = (String) session.getAttribute("buyerEmail");

        if (buyerEmail == null) {
            response.sendRedirect("userSignIn.jsp");
            return;
        }

        Buyer buyer = BuyerServices.getProfileDetails(buyerEmail);
        request.setAttribute("buyer", buyer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editBuyerProfile.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String buyerEmail = (String) session.getAttribute("buyerEmail");

        if (buyerEmail == null) {
            response.sendRedirect("userSignIn.jsp");
            return;
        }

        String name = request.getParameter("name");
        String mobileNumber = request.getParameter("mobileNumber");
        String shippingAddress = request.getParameter("shippingAddress");

        Buyer buyer = new Buyer(name, buyerEmail, mobileNumber, shippingAddress);
        boolean success = BuyerServices.updateProfile(buyer);

        if (success) {
            request.setAttribute("message", "Profile updated successfully!");
        } else {
            request.setAttribute("message", "Failed to update profile. Please try again.");
        }

        Buyer updatedBuyer = BuyerServices.getProfileDetails(buyerEmail);
        request.setAttribute("buyer", updatedBuyer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("buyerProfile.jsp");
        dispatcher.forward(request, response);
    }
}