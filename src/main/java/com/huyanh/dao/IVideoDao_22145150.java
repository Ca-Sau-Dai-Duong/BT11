package com.huyanh.dao;

import java.util.List;
import com.huyanh.entity.Videos_22145150;

public interface IVideoDao_22145150 {
    List<Videos_22145150> findPage(int page, int size, boolean onlyActive);
    long count(boolean onlyActive);
    List<Videos_22145150> findByCategory(int categoryId, int page, int size);
    long countByCategory(int categoryId);
    Videos_22145150 findById(String id);
    void insert(Videos_22145150 v);
    void update(Videos_22145150 v);
    void delete(String id);
}
