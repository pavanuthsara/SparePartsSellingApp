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

@WebServlet("/SellerSignUp")
public class SellerSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SellerSignUp() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Seller sign up servlet called!");
		
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mobileNumber = request.getParameter("mobileNumber");
		String storeName = request.getParameter("storeName");
		
		Seller seller = new Seller(name, email, password, mobileNumber, storeName);
		SellerServices.register(seller);
		
		//create seller session
		HttpSession session = request.getSession();
		session.setAttribute("sellerEmail", email);
		session.setAttribute("sellerName", name);
		
		response.sendRedirect("sellerDashboard.jsp");
		
	}

}
