package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.BuyerServices;

@WebServlet("/BuyerDeleteProfile")
public class BuyerDeleteProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BuyerDeleteProfile() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String buyerEmail = (String) session.getAttribute("buyerEmail");

        if (buyerEmail == null) {
            response.sendRedirect("userSignIn.jsp");
            return;
        }

        boolean success = BuyerServices.deleteProfile(buyerEmail);

        if (success) {
            session.invalidate(); // Clear session
            response.sendRedirect("userSignIn.jsp?message=Profile+deleted+successfully");
        } else {
            response.sendRedirect("buyerProfile.jsp?message=Failed+to+delete+profile");
        }
    }
}