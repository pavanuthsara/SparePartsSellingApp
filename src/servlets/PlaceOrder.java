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

import models.CartItem;
import models.Order;
import services.OrderService;

@WebServlet("/PlaceOrder")
public class PlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PlaceOrder() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String shippingAddress = request.getParameter("shippingAddress");
		String specialNote = request.getParameter("specialNote");
		String mobileNumber = request.getParameter("mobileNumber");
		String paymentMethod = request.getParameter("paymentMethod");
		String buyerEmail = request.getParameter("buyerEmail");
		String date = request.getParameter("date");
		Double totalCost = Double.parseDouble(request.getParameter("totalCost")) ;
		String fastDelivery = request.getParameter("fastDelivery");
		boolean fdelivery = false;
		
		HttpSession session = request.getSession();
		ArrayList<CartItem> cartItems = (ArrayList<CartItem>) session.getAttribute("cartItems");

		if (fastDelivery != null) {
			totalCost += 500;
			fdelivery = true;
		}
		
		Order order = new Order(cartItems, shippingAddress, specialNote, mobileNumber, paymentMethod, totalCost, date, buyerEmail, fdelivery );
		boolean result = OrderService.placeOrder(order);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("GetBuyerOrders");
		dispatcher.forward(request, response);
		
	}

}
