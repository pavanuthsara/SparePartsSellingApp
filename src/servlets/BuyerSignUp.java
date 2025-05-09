package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		System.out.println("User password : " + password);
	}

}
