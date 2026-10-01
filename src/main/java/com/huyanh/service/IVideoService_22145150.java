package com.huyanh.service;

import java.util.List;
import com.huyanh.entity.Category_22145150;
import com.huyanh.entity.Videos_22145150;
import com.huyanh.model.CategoryBlock_22145150;

public interface IVideoService_22145150 {
    List<Videos_22145150> getPage(int page, int size, boolean onlyActive);
    int totalPages(int size, boolean onlyActive);
    long countAll();
    Videos_22145150 findById(String id);
    void insert(Videos_22145150 v);
    void update(Videos_22145150 v);
    void delete(String id);
    CategoryBlock_22145150 buildBlock(Category_22145150 c, int page, int size);
}
