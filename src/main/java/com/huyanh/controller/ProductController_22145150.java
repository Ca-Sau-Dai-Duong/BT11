package com.huyanh.controller;

import com.huyanh.service.IVideoService_22145150;
import com.huyanh.service.impl.VideoServiceImpl_22145150;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.huyanh.util.WebUtil_22145150;

/** Menu "Sản phẩm": tất cả video (6 / trang) */
@WebServlet("/products")
public class ProductController_22145150 extends HttpServlet {
    private static final int SIZE = 6;
    private final IVideoService_22145150 videoService = new VideoServiceImpl_22145150();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int totalPages = videoService.totalPages(SIZE, true);
        int page = Math.min(Math.max(WebUtil_22145150.toInt(req.getParameter("page"), 1), 1), totalPages);
        req.setAttribute("videos", videoService.getPage(page, SIZE, true));
        req.setAttribute("page", page);
        req.setAttribute("totalPages", totalPages);
        req.getRequestDispatcher("/WEB-INF/views/web/products.jsp").forward(req, resp);
    }
}
