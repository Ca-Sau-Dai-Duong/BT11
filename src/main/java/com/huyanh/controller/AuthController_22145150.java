package com.huyanh.controller;

import com.huyanh.service.IUserService_22145150;
import com.huyanh.service.impl.UserServiceImpl_22145150;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.huyanh.entity.Users_22145150;
import com.huyanh.util.EmailUtil_22145150;

/** Câu 2: Đăng ký (OTP) / Đăng nhập / Đăng xuất (Session) */
@WebServlet({"/login", "/logout", "/register", "/verify-otp"})
public class AuthController_22145150 extends HttpServlet {
    private static final long OTP_LIFETIME_MS = 5 * 60 * 1000; // OTP sống 5 phút
    private final IUserService_22145150 userService = new UserServiceImpl_22145150();

    private void forward(HttpServletRequest req, HttpServletResponse resp, String view) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/web/" + view + ".jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/logout":
                HttpSession s = req.getSession(false);
                if (s != null) s.invalidate();
                resp.sendRedirect(req.getContextPath() + "/login");
                break;
            case "/register":
                forward(req, resp, "register");
                break;
            case "/verify-otp":
                if (req.getSession().getAttribute("pendingUser") == null) {
                    resp.sendRedirect(req.getContextPath() + "/register");
                } else {
                    forward(req, resp, "verify-otp");
                }
                break;
            default: // /login
                forward(req, resp, "login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/register": doRegister(req, resp); break;
            case "/verify-otp": doVerify(req, resp); break;
            default: doLogin(req, resp);
        }
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Users_22145150 u = userService.login(username, password);
        if (u == null) {
            req.setAttribute("error", "Sai tên đăng nhập / mật khẩu hoặc tài khoản chưa kích hoạt!");
            req.setAttribute("username", username);
            forward(req, resp, "login"); // đăng nhập thất bại -> quay lại trang đăng nhập
            return;
        }
        req.getSession().setAttribute("user", u);
        if (Boolean.TRUE.equals(u.getAdmin())) {
            resp.sendRedirect(req.getContextPath() + "/admin/home"); // admin -> trang chủ Admin
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");       // user thường -> trang chủ User
        }
    }

    private void doRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        String error = null;
        if (username == null || username.trim().isEmpty() || password == null || password.isEmpty()
                || email == null || email.trim().isEmpty()) {
            error = "Vui lòng nhập đủ Username, Password, Email!";
        } else if (userService.exists(username.trim())) {
            error = "Username đã tồn tại!";
        }
        if (error != null) {
            req.setAttribute("error", error);
            forward(req, resp, "register");
            return;
        }

        Users_22145150 u = new Users_22145150();
        u.setUsername(username.trim());
        u.setPassword(password);
        u.setFullname(fullname);
        u.setEmail(email.trim());
        u.setPhone(phone);
        u.setAdmin(false);
        u.setActive(false); // chưa kích hoạt, nhập đúng OTP mới lưu + Active = true

        String otp = userService.generateOtp();
        HttpSession session = req.getSession();
        session.setAttribute("pendingUser", u);
        session.setAttribute("otp", otp);
        session.setAttribute("otpExpire", System.currentTimeMillis() + OTP_LIFETIME_MS);

        boolean sent = EmailUtil_22145150.send(u.getEmail(), "Mã OTP kích hoạt tài khoản",
                "Xin chào " + fullname + ",\nMã OTP của bạn là: " + otp + " (hiệu lực 5 phút).");
        System.out.println(">>> OTP của " + u.getUsername() + " = " + otp + (sent ? " (đã gửi mail)" : " (GỬI MAIL THẤT BẠI - dùng OTP này ở console)"));
        resp.sendRedirect(req.getContextPath() + "/verify-otp");
    }

    private void doVerify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Users_22145150 u = (Users_22145150) session.getAttribute("pendingUser");
        String otp = (String) session.getAttribute("otp");
        Long expire = (Long) session.getAttribute("otpExpire");
        if (u == null || otp == null || expire == null) {
            resp.sendRedirect(req.getContextPath() + "/register");
            return;
        }
        String input = req.getParameter("otp");
        if (System.currentTimeMillis() > expire) {
            req.setAttribute("error", "OTP đã hết hạn, vui lòng đăng ký lại!");
            forward(req, resp, "verify-otp");
        } else if (!otp.equals(input == null ? "" : input.trim())) {
            req.setAttribute("error", "OTP không đúng!");
            forward(req, resp, "verify-otp");
        } else {
            u.setActive(true);
            userService.register(u);
            session.removeAttribute("pendingUser");
            session.removeAttribute("otp");
            session.removeAttribute("otpExpire");
            resp.sendRedirect(req.getContextPath() + "/login?registered=1");
        }
    }
}
