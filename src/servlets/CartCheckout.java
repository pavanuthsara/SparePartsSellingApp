package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Cart;

@WebServlet("/CartCheckout")
public class CartCheckout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartCheckout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		request.setAttribute("cartItems", cart.getItems());
		request.setAttribute("totalPrice", cart.checkout());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
		dispatcher.forward(request, response);
	}


}
