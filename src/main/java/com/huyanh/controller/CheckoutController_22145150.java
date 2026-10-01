package com.huyanh.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.huyanh.entity.Order_22145150;
import com.huyanh.entity.Users_22145150;
import com.huyanh.service.ICartService_22145150;
import com.huyanh.service.IOrderService_22145150;
import com.huyanh.service.impl.CartServiceImpl_22145150;
import com.huyanh.service.impl.OrderServiceImpl_22145150;

/** Câu 2 (bổ sung): Thanh toán COD */
@WebServlet({"/checkout", "/checkout/place", "/checkout/success"})
public class CheckoutController_22145150 extends HttpServlet {
    private final ICartService_22145150 cartService = new CartServiceImpl_22145150();
    private final IOrderService_22145150 orderService = new OrderServiceImpl_22145150();

    private Users_22145150 requireLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        Users_22145150 u = session == null ? null : (Users_22145150) session.getAttribute("user");
        if (u == null) resp.sendRedirect(req.getContextPath() + "/login");
        return u;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users_22145150 u = requireLogin(req, resp);
        if (u == null) return;

        if ("/checkout/success".equals(req.getServletPath())) {
            req.setAttribute("orderId", req.getParameter("orderId"));
            req.getRequestDispatcher("/WEB-INF/views/web/checkout-success.jsp").forward(req, resp);
            return;
        }

        // /checkout : xem trước đơn hàng trước khi đặt
        var cart = cartService.getCart(u.getUsername());
        if (cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }
        req.setAttribute("cartItems", cart);
        req.setAttribute("user", u);
        req.getRequestDispatcher("/WEB-INF/views/web/checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users_22145150 u = requireLogin(req, resp);
        if (u == null) return;

        String receiverName = req.getParameter("receiverName");
        String receiverPhone = req.getParameter("receiverPhone");
        String receiverAddress = req.getParameter("receiverAddress");
        String note = req.getParameter("note");

        if (isBlank(receiverName) || isBlank(receiverPhone) || isBlank(receiverAddress)) {
            req.setAttribute("error", "Vui lòng nhập đủ Họ tên, Số điện thoại, Địa chỉ nhận hàng!");
            req.setAttribute("cartItems", cartService.getCart(u.getUsername()));
            req.setAttribute("user", u);
            req.getRequestDispatcher("/WEB-INF/views/web/checkout.jsp").forward(req, resp);
            return;
        }

        Order_22145150 order = orderService.checkoutCOD(u.getUsername(), receiverName.trim(),
                receiverPhone.trim(), receiverAddress.trim(), note);
        if (order == null) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/checkout/success?orderId=" + order.getOrderId());
    }

    private boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
}
