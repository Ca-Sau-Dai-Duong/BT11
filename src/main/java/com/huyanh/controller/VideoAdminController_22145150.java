package com.huyanh.controller;

import com.huyanh.service.ICategoryService_22145150;
import com.huyanh.service.IVideoService_22145150;
import com.huyanh.service.impl.CategoryServiceImpl_22145150;
import com.huyanh.service.impl.VideoServiceImpl_22145150;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.huyanh.entity.Videos_22145150;
import com.huyanh.service.*;
import com.huyanh.util.WebUtil_22145150;

/** Câu 2 (2.5đ): CRUD Videos, phân trang 6 video / trang */
@WebServlet({"/admin/videos", "/admin/videos/add", "/admin/videos/edit", "/admin/videos/save", "/admin/videos/delete"})
public class VideoAdminController_22145150 extends HttpServlet {
    private static final int SIZE = 6;
    private final IVideoService_22145150 videoService = new VideoServiceImpl_22145150();
    private final ICategoryService_22145150 categoryService = new CategoryServiceImpl_22145150();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ctx = req.getContextPath();
        switch (req.getServletPath()) {
            case "/admin/videos/add":
                showForm(req, resp, new Videos_22145150(), true);
                break;
            case "/admin/videos/edit":
                Videos_22145150 v = videoService.findById(req.getParameter("id"));
                if (v == null) resp.sendRedirect(ctx + "/admin/videos");
                else showForm(req, resp, v, false);
                break;
            case "/admin/videos/delete":
                videoService.delete(req.getParameter("id"));
                resp.sendRedirect(ctx + "/admin/videos?page=" + WebUtil_22145150.toInt(req.getParameter("page"), 1));
                break;
            default: // list
                int totalPages = videoService.totalPages(SIZE, false);
                int page = Math.min(Math.max(WebUtil_22145150.toInt(req.getParameter("page"), 1), 1), totalPages);
                req.setAttribute("videos", videoService.getPage(page, SIZE, false));
                req.setAttribute("page", page);
                req.setAttribute("totalPages", totalPages);
                req.getRequestDispatcher("/WEB-INF/views/admin/video-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isNew = "true".equals(req.getParameter("isNew"));
        Videos_22145150 v = new Videos_22145150();
        v.setVideoId(req.getParameter("videoId"));
        v.setTitle(req.getParameter("title"));
        v.setPoster(req.getParameter("poster"));
        v.setViews(WebUtil_22145150.toInt(req.getParameter("views"), 0));
        v.setDescription(req.getParameter("description"));
        v.setActive(req.getParameter("active") != null);
        v.setCategory(categoryService.findById(WebUtil_22145150.toInt(req.getParameter("categoryId"), 0)));

        if (v.getVideoId() == null || v.getVideoId().trim().isEmpty()) {
            req.setAttribute("error", "Mã video không được để trống!");
            showForm(req, resp, v, isNew);
            return;
        }
        if (isNew) {
            if (videoService.findById(v.getVideoId()) != null) {
                req.setAttribute("error", "Mã video đã tồn tại!");
                showForm(req, resp, v, true);
                return;
            }
            videoService.insert(v);
        } else {
            videoService.update(v);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/videos");
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp, Videos_22145150 v, boolean isNew)
            throws ServletException, IOException {
        req.setAttribute("video", v);
        req.setAttribute("isNew", isNew);
        req.setAttribute("categories", categoryService.findAll());
        req.getRequestDispatcher("/WEB-INF/views/admin/video-form.jsp").forward(req, resp);
    }
}
