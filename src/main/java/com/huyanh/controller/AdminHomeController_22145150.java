package com.huyanh.controller;

import com.huyanh.service.ICategoryService_22145150;
import com.huyanh.service.IVideoService_22145150;
import com.huyanh.service.impl.CategoryServiceImpl_22145150;
import com.huyanh.service.impl.VideoServiceImpl_22145150;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.huyanh.service.*;

/** Trang chủ Admin */
@WebServlet("/admin/home")
public class AdminHomeController_22145150 extends HttpServlet {
    private final IVideoService_22145150 videoService = new VideoServiceImpl_22145150();
    private final ICategoryService_22145150 categoryService = new CategoryServiceImpl_22145150();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("totalVideos", videoService.countAll());
        req.setAttribute("totalCategories", categoryService.findAll().size());
        req.getRequestDispatcher("/WEB-INF/views/admin/home.jsp").forward(req, resp);
    }
}
