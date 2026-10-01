package com.huyanh.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.huyanh.entity.Users_22145150;
import com.huyanh.service.ICartService_22145150;
import com.huyanh.service.impl.CartServiceImpl_22145150;
import com.huyanh.util.WebUtil_22145150;

/** Câu 1 (bổ sung): Giỏ hàng - thêm, xóa, sửa số lượng */
@WebServlet({"/cart", "/cart/add", "/cart/update", "/cart/remove"})
public class CartController_22145150 extends HttpServlet {
    private final ICartService_22145150 cartService = new CartServiceImpl_22145150();

    private String requireLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        Users_22145150 u = session == null ? null : (Users_22145150) session.getAttribute("user");
        if (u == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return null;
        }
        return u.getUsername();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = requireLogin(req, resp);
        if (username == null) return;

        switch (req.getServletPath()) {
            case "/cart/remove":
                cartService.remove(username, WebUtil_22145150.toInt(req.getParameter("id"), 0));
                resp.sendRedirect(req.getContextPath() + "/cart");
                break;
            default: // /cart : xem giỏ hàng
                req.setAttribute("cartItems", cartService.getCart(username));
                req.getRequestDispatcher("/WEB-INF/views/web/cart.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = requireLogin(req, resp);
        if (username == null) return;

        if ("/cart/add".equals(req.getServletPath())) {
            String videoId = req.getParameter("videoId");
            int qty = WebUtil_22145150.toInt(req.getParameter("quantity"), 1);
            String error = cartService.addToCart(username, videoId, qty);
            String back = req.getParameter("back"); // quay lại trang vừa bấm "Thêm vào giỏ"
            String redirect = (back != null && !back.isEmpty()) ? back : req.getContextPath() + "/cart";
            redirect += (redirect.contains("?") ? "&" : "?") + (error == null ? "added=1" : "error=" + java.net.URLEncoder.encode(error, "UTF-8"));
            resp.sendRedirect(redirect);
        } else { // /cart/update
            int cartItemId = WebUtil_22145150.toInt(req.getParameter("cartItemId"), 0);
            int qty = WebUtil_22145150.toInt(req.getParameter("quantity"), 1);
            String error = cartService.updateQuantity(username, cartItemId, qty);
            req.setAttribute("cartItems", cartService.getCart(username));
            if (error != null) req.setAttribute("error", error);
            req.getRequestDispatcher("/WEB-INF/views/web/cart.jsp").forward(req, resp);
        }
    }
}
