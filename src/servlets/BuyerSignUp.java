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

@WebServlet("/BuyerSignUp")
public class BuyerSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public BuyerSignUp() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Buyer sign up servlet called!");
		
		String name = request.getParameter("name"); 
		String email = request.getParameter("email"); 
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String mobileNumber = request.getParameter("mobileNumber");
		
		Buyer buyer = new Buyer(name, email, password, mobileNumber,  address);
		BuyerServices.register(buyer);

		HttpSession session = request.getSession();
		session.setAttribute("buyerEmail", email);
		session.setAttribute("buyerName", name);
		
		response.sendRedirect("buyerDashboard.jsp");
	
	}

}
