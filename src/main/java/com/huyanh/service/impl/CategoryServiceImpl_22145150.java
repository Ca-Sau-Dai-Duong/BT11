package com.huyanh.service.impl;

import com.huyanh.dao.ICategoryDao_22145150;
import com.huyanh.dao.impl.CategoryDaoImpl_22145150;
import com.huyanh.service.ICategoryService_22145150;
import java.util.List;
import com.huyanh.entity.Category_22145150;

public class CategoryServiceImpl_22145150 implements ICategoryService_22145150 {
    private final ICategoryDao_22145150 dao = new CategoryDaoImpl_22145150();
    @Override
    public List<Category_22145150> findAll() { return dao.findAll(); }
    @Override
    public Category_22145150 findById(int id) { return dao.findById(id); }
}
