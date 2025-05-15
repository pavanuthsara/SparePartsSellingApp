package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ClearCart")
public class ClearCart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClearCart() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String buyerEmail = (String) session.getAttribute("buyerEmail");

        if (buyerEmail == null) {
            response.sendRedirect("userSignIn.jsp");
            return;
        }

        // Clear cart-related session attributes
        session.removeAttribute("cart");
        session.removeAttribute("noOfCartItems");

        // Redirect to GetAllProducts with a success message
        response.sendRedirect("GetAllProducts?message=Cart+cleared+successfully");
    }
}