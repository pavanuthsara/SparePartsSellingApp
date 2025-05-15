package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Buyer;
import services.BuyerServices;

@WebServlet("/BuyerSignIn")
public class BuyerSignIn extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BuyerSignIn() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Buyer buyer = BuyerServices.login(email, password);
        if (buyer == null) {
            // Set error message in session or request
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", "Invalid user email or password");
            // Redirect back to sign-in page
            response.sendRedirect("userSignIn.jsp");
        } else {
            System.out.println("Buyer login success");

            HttpSession session = request.getSession();
            session.setAttribute("buyerEmail", buyer.getEmail());
            session.setAttribute("buyerName", buyer.getName());

            response.sendRedirect("buyerDashboard.jsp");
        }
    }
}