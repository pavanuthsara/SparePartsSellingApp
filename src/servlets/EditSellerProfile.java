package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Seller;
import services.SellerServices;

@WebServlet("/EditSellerProfile")
public class EditSellerProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditSellerProfile() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sellerEmail = (String) session.getAttribute("sellerEmail");

        if (sellerEmail == null) {
            response.sendRedirect("sellerSignIn.jsp");
            return;
        }

        Seller seller = SellerServices.getProfileDetails(sellerEmail);
        request.setAttribute("seller", seller);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editSellerProfile.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sellerEmail = (String) session.getAttribute("sellerEmail");

        if (sellerEmail == null) {
            response.sendRedirect("sellerSignIn.jsp");
            return;
        }

        String name = request.getParameter("name");
        String mobileNumber = request.getParameter("mobileNumber");
        String storeName = request.getParameter("storeName");

        Seller seller = new Seller(name, sellerEmail, mobileNumber, storeName);
        boolean success = SellerServices.updateProfile(seller);

        if (success) {
            request.setAttribute("message", "Profile updated successfully!");
        } else {
            request.setAttribute("message", "Failed to update profile. Please try again.");
        }

        Seller updatedSeller = SellerServices.getProfileDetails(sellerEmail);
        request.setAttribute("seller", updatedSeller);
        RequestDispatcher dispatcher = request.getRequestDispatcher("sellerProfile.jsp");
        dispatcher.forward(request, response);
    }
}