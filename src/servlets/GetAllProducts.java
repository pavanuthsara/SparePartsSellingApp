package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.SparePart;
import services.SparePartServices;


@WebServlet("/GetAllProducts")
public class GetAllProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetAllProducts() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<SparePart> result = SparePartServices.getAllProducts();
		System.out.println(result);
		
		request.setAttribute("products", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("spareParts.jsp");
		dispatcher.forward(request, response);
	}
	


}
