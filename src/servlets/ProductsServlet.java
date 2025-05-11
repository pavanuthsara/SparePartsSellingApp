package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.SparePart;
import services.SparePartServices;

@WebServlet("/Products")
public class ProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProductsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch all spare parts
        List<SparePart> spareParts = SparePartServices.getAllSpareParts();

        // Set the spare parts list as a request attribute
        request.setAttribute("spareParts", spareParts);

        // Forward to products.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("products.jsp");
        dispatcher.forward(request, response);
    }
}