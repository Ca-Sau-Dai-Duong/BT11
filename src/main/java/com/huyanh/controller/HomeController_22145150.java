package com.huyanh.controller;

import com.huyanh.service.ICategoryService_22145150;
import com.huyanh.service.IVideoService_22145150;
import com.huyanh.service.impl.CategoryServiceImpl_22145150;
import com.huyanh.service.impl.VideoServiceImpl_22145150;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.huyanh.entity.Category_22145150;
import com.huyanh.model.CategoryBlock_22145150;
import com.huyanh.service.*;
import com.huyanh.util.WebUtil_22145150;

/** Câu 4 + Câu 5: trang home user - video theo từng category, 3 video / trang */
@WebServlet("/home")
public class HomeController_22145150 extends HttpServlet {
    private static final int SIZE = 3;
    private final ICategoryService_22145150 categoryService = new CategoryServiceImpl_22145150();
    private final IVideoService_22145150 videoService = new VideoServiceImpl_22145150();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryBlock_22145150> blocks = new ArrayList<>();
        for (Category_22145150 c : categoryService.findAll()) {
            // mỗi category có 1 tham số trang riêng: p<categoryId>
            int page = WebUtil_22145150.toInt(req.getParameter("p" + c.getCategoryId()), 1);
            blocks.add(videoService.buildBlock(c, page, SIZE));
        }
        // baseQuery: giữ nguyên trang hiện tại của các category khác khi bấm phân trang 1 category
        for (CategoryBlock_22145150 b : blocks) {
            StringBuilder qs = new StringBuilder();
            for (CategoryBlock_22145150 o : blocks) {
                if (o != b) qs.append("p").append(o.getCategory().getCategoryId()).append("=").append(o.getPage()).append("&");
            }
            b.setBaseQuery(qs.toString());
        }
        req.setAttribute("blocks", blocks);
        req.getRequestDispatcher("/WEB-INF/views/web/home.jsp").forward(req, resp);
    }
}
