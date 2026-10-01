package com.huyanh.dao.impl;

import com.huyanh.dao.ICategoryDao_22145150;
import java.util.List;
import javax.persistence.EntityManager;
import com.huyanh.entity.Category_22145150;
import com.huyanh.util.JpaUtil_22145150;

public class CategoryDaoImpl_22145150 implements ICategoryDao_22145150 {
    @Override
    public List<Category_22145150> findAll() {
        EntityManager em = JpaUtil_22145150.getEntityManager();
        try {
            return em.createQuery("select c from Category c order by c.categoryId", Category_22145150.class).getResultList();
        } finally { em.close(); }
    }
    @Override
    public Category_22145150 findById(int id) {
        EntityManager em = JpaUtil_22145150.getEntityManager();
        try { return em.find(Category_22145150.class, id); }
        finally { em.close(); }
    }
}
