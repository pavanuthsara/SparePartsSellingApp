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

@WebServlet("/BuyerProfile")
public class BuyerProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BuyerProfile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Buyer profile servlet called");
		
		HttpSession session = request.getSession();
		String buyerEmail = (String)session.getAttribute("buyerEmail");
		
		System.out.println("Buyer email : " + buyerEmail);
		
		Buyer buyer = BuyerServices.getProfileDetails(buyerEmail);
		
		request.setAttribute("buyer", buyer);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("buyerProfile.jsp");
		dispatcher.forward(request, response);
		
	}

}
