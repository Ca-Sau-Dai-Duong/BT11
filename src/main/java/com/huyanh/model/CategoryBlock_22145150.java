package com.huyanh.model;

import java.util.List;
import com.huyanh.entity.Category_22145150;
import com.huyanh.entity.Videos_22145150;

/** 1 khối category ở trang home: danh sách video của trang hiện tại + tổng số video (Câu 5) */
public class CategoryBlock_22145150 {
    private Category_22145150 category;
    private List<Videos_22145150> videos;
    private long total;
    private int page;
    private int totalPages;
    private String baseQuery = "";

    public CategoryBlock_22145150(Category_22145150 category, List<Videos_22145150> videos,
                                  long total, int page, int totalPages) {
        this.category = category; this.videos = videos; this.total = total;
        this.page = page; this.totalPages = totalPages;
    }
    public Category_22145150 getCategory() { return category; }
    public List<Videos_22145150> getVideos() { return videos; }
    public long getTotal() { return total; }
    public int getPage() { return page; }
    public int getTotalPages() { return totalPages; }
    public String getBaseQuery() { return baseQuery; }
    public void setBaseQuery(String baseQuery) { this.baseQuery = baseQuery; }
}
