package com.huyanh.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import com.huyanh.entity.Users_22145150;

/** Chỉ admin mới vào được /admin/* */
@WebFilter("/admin/*")
public class AuthFilter_22145150 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        Users_22145150 u = session == null ? null : (Users_22145150) session.getAttribute("user");
        if (u != null && Boolean.TRUE.equals(u.getAdmin())) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
