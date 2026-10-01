package com.huyanh.service.impl;

import com.huyanh.dao.IVideoDao_22145150;
import com.huyanh.dao.impl.VideoDaoImpl_22145150;
import com.huyanh.service.IVideoService_22145150;
import java.util.Collections;
import java.util.List;
import com.huyanh.entity.Category_22145150;
import com.huyanh.entity.Videos_22145150;
import com.huyanh.model.CategoryBlock_22145150;

public class VideoServiceImpl_22145150 implements IVideoService_22145150 {
    private final IVideoDao_22145150 dao = new VideoDaoImpl_22145150();

    @Override
    public List<Videos_22145150> getPage(int page, int size, boolean onlyActive) { return dao.findPage(page, size, onlyActive); }
    @Override
    public int totalPages(int size, boolean onlyActive) {
        return Math.max(1, (int) Math.ceil(dao.count(onlyActive) / (double) size));
    }
    @Override
    public long countAll() { return dao.count(false); }
    @Override
    public Videos_22145150 findById(String id) { return dao.findById(id); }
    @Override
    public void insert(Videos_22145150 v) { dao.insert(v); }
    @Override
    public void update(Videos_22145150 v) { dao.update(v); }
    @Override
    public void delete(String id) { dao.delete(id); }

    /** Dựng 1 khối category cho trang home (phân trang theo size video / trang) */
    @Override
    public CategoryBlock_22145150 buildBlock(Category_22145150 c, int page, int size) {
        long total = dao.countByCategory(c.getCategoryId());
        int totalPages = Math.max(1, (int) Math.ceil(total / (double) size));
        page = Math.min(Math.max(page, 1), totalPages);
        List<Videos_22145150> videos = total == 0 ? Collections.<Videos_22145150>emptyList()
                : dao.findByCategory(c.getCategoryId(), page, size);
        return new CategoryBlock_22145150(c, videos, total, page, totalPages);
    }
}
