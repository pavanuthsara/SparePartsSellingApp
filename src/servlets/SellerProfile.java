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

@WebServlet("/SellerProfile")
public class SellerProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SellerProfile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Seller profile servlet called");
		
		HttpSession session = request.getSession();
		String sellerEmail = (String)session.getAttribute("sellerEmail");
		
		System.out.println("Seller email : " + sellerEmail);
		
		Seller seller = SellerServices.getProfileDetails(sellerEmail);
		
		request.setAttribute("seller", seller);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("sellerProfile.jsp");
		dispatcher.forward(request, response);
	}

}
