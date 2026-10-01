package com.huyanh.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.huyanh.entity.Order_22145150;
import com.huyanh.entity.OrderStatus_22145150;
import com.huyanh.entity.Users_22145150;
import com.huyanh.service.IOrderService_22145150;
import com.huyanh.service.impl.OrderServiceImpl_22145150;
import com.huyanh.util.WebUtil_22145150;

/** Câu 3 (bổ sung): Lịch sử đặt hàng - lọc theo trạng thái */
@WebServlet({"/orders", "/orders/detail"})
public class OrderHistoryController_22145150 extends HttpServlet {
    private final IOrderService_22145150 orderService = new OrderServiceImpl_22145150();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Users_22145150 u = session == null ? null : (Users_22145150) session.getAttribute("user");
        if (u == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        if ("/orders/detail".equals(req.getServletPath())) {
            int id = WebUtil_22145150.toInt(req.getParameter("id"), 0);
            Order_22145150 order = orderService.getDetail(id, u.getUsername());
            if (order == null) {
                resp.sendRedirect(req.getContextPath() + "/orders");
                return;
            }
            // Đọc trạng thái mới nhất trực tiếp từ DB để chắc chắn luôn đúng với dữ liệu hiện tại
            order.setStatus(orderService.getLiveStatus(order.getOrderId()));
            req.setAttribute("order", order);
            req.getRequestDispatcher("/WEB-INF/views/web/order-detail.jsp").forward(req, resp);
            return;
        }

        // /orders : danh sách, lọc theo trạng thái
        String statusParam = req.getParameter("status");
        OrderStatus_22145150 status = null;
        if (statusParam != null && !statusParam.isEmpty()) {
            try { status = OrderStatus_22145150.valueOf(statusParam); } catch (IllegalArgumentException ignored) {}
        }
        req.setAttribute("orders", orderService.getHistory(u.getUsername(), status));
        req.setAttribute("statuses", OrderStatus_22145150.values());
        req.setAttribute("selectedStatus", statusParam);
        req.getRequestDispatcher("/WEB-INF/views/web/order-history.jsp").forward(req, resp);
    }
}
