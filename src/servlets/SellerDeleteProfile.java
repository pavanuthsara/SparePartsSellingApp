package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.SellerServices;

@WebServlet("/SellerDeleteProfile")
public class SellerDeleteProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SellerDeleteProfile() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sellerEmail = (String) session.getAttribute("sellerEmail");

        if (sellerEmail == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        boolean success = SellerServices.deleteProfile(sellerEmail);

        if (success) {
            session.invalidate();
            response.sendRedirect("sellerSignIn.jsp?message=Profile+deleted+successfully");
        } else {
            response.sendRedirect("sellerProfile.jsp?message=Failed+to+delete+profile");
        }
    }
}