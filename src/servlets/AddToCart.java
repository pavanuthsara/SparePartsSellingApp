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
import models.CartItem;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cart cart = new Cart();
	private int noOfItems = 0;

    public AddToCart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("productId"));
		double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String title = request.getParameter("title");
		
		System.out.println("product id : " + productId);
		System.out.println("title : " + title);
		System.out.println("unit price : " + unitPrice);
		System.out.println("quantity : " + quantity);
		
		CartItem cartItem = new CartItem(productId, title, quantity, unitPrice );
		
		cart.addItemToCart(cartItem);
		noOfItems += quantity;
		cart.displayCart();
		
		HttpSession session = request.getSession();
		session.setAttribute("cart", cart);
		
		request.setAttribute("noOfCartItems", noOfItems);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("GetAllProducts");
		dispatcher.forward(request, response);
	}

}
