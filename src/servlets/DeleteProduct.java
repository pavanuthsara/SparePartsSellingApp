package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.SparePartServices;

@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteProduct() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Delete product servlet called");

        int productId = Integer.parseInt(request.getParameter("productId"));
        SparePartServices.deleteSparePart(productId);

        response.sendRedirect("GetSellerProducts");
    }
}