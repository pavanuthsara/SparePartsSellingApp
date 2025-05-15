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

import models.Order;
import services.OrderService;

@WebServlet("/GetBuyerOrders")
public class GetBuyerOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetBuyerOrders() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Get orders related to buyer servlet called");
		
		HttpSession session = request.getSession();
        String buyerEmail = (String) session.getAttribute("buyerEmail");

        ArrayList<Order> result = OrderService.getOrdersOfBuyer(buyerEmail);
		
		request.setAttribute("orders", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("buyerOrders.jsp");
		dispatcher.forward(request, response);
	}

}
