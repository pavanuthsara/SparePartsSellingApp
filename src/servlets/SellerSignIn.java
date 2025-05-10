package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Seller;
import services.SellerServices;

@WebServlet("/SellerSignIn")
public class SellerSignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SellerSignIn() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Seller seller = SellerServices.login(email, password);
		if(seller == null) {
			System.out.println("Seller not available");
		}else {
			System.out.println("Seller login success");
			
			HttpSession session = request.getSession();
			session.setAttribute("sellerEmail", seller.getEmail());
			session.setAttribute("sellerName", seller.getName());
			
			response.sendRedirect("sellerDashboard.jsp");
		}
	}

}
