package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.SparePart;
import services.SellerServices;
import services.SparePartServices;

@WebServlet("/GetSellerProducts")
public class GetSellerProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetSellerProducts() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Get product related to seller servlet called");
		
		HttpSession session = request.getSession();
        String sellerEmail = (String) session.getAttribute("sellerEmail");

		ArrayList<SparePart> result = SparePartServices.getSellerProduct(sellerEmail);
		System.out.println(result);
		
		request.setAttribute("products", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("sellerDashboard.jsp");
		dispatcher.forward(request, response);
	}


}
