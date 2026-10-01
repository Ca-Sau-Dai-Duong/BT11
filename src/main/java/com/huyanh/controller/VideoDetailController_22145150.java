package com.huyanh.controller;

import com.huyanh.service.IVideoService_22145150;
import com.huyanh.service.impl.VideoServiceImpl_22145150;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.huyanh.entity.Videos_22145150;

/** Câu 3: trang chi tiết 1 video  (/video?id=V01) */
@WebServlet("/video")
public class VideoDetailController_22145150 extends HttpServlet {
    private final IVideoService_22145150 videoService = new VideoServiceImpl_22145150();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Videos_22145150 v = id == null ? null : videoService.findById(id);
        if (v == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }
        req.setAttribute("video", v);
        req.getRequestDispatcher("/WEB-INF/views/web/detail.jsp").forward(req, resp);
    }
}
