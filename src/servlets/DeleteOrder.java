package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.OrderService;

@WebServlet("/DeleteOrder")
public class DeleteOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteOrder() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        boolean success = OrderService.deleteOrder(Integer.parseInt(orderId), buyerEmail);

        if (success) {
            response.sendRedirect("GetBuyerOrders?message=Order+deleted+successfully");
        } else {
            response.sendRedirect("GetBuyerOrders?message=Failed+to+delete+order");
        }
    }
}