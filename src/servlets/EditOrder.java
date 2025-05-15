package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Order;
import services.OrderService;

@WebServlet("/EditOrder")
public class EditOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditOrder() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String buyerEmail = (String) session.getAttribute("buyerEmail");

        if (buyerEmail == null) {
            response.sendRedirect("userSignIn.jsp");
            return;
        }

        String orderId = request.getParameter("orderId");
        if (orderId == null || orderId.isEmpty()) {
            response.sendRedirect("GetBuyerOrders?message=Invalid+order+ID");
            return;
        }

        Order order = OrderService.getOrderById(Integer.parseInt(orderId), buyerEmail);
        if (order == null) {
            response.sendRedirect("GetBuyerOrders?message=Order+not+found");
            return;
        }

        request.setAttribute("order", order);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editOrder.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String buyerEmail = (String) session.getAttribute("buyerEmail");

        if (buyerEmail == null) {
            response.sendRedirect("userSignIn.jsp");
            return;
        }

        String orderId = request.getParameter("orderId");
        String shippingAddress = request.getParameter("shippingAddress");
        String specialNote = request.getParameter("specialNote");
        String mobileNumber = request.getParameter("mobileNumber");
        String paymentMethod = request.getParameter("paymentMethod");
        boolean fastDelivery = request.getParameter("fastDelivery") != null;

        Order order = new Order(Integer.parseInt(orderId), shippingAddress, specialNote, mobileNumber, paymentMethod, 0.0, null, buyerEmail, fastDelivery);
        boolean success = OrderService.updateOrder(order);

        if (success) {
            response.sendRedirect("GetBuyerOrders?message=Order+updated+successfully");
        } else {
            response.sendRedirect("GetBuyerOrders?message=Failed+to+update+order");
        }
    }
}