package com.huyanh.dao.impl;

import com.huyanh.dao.IUserDao_22145150;
import javax.persistence.EntityManager;
import com.huyanh.entity.Users_22145150;
import com.huyanh.util.JpaUtil_22145150;

public class UserDaoImpl_22145150 implements IUserDao_22145150 {
    @Override
    public Users_22145150 findById(String username) {
        EntityManager em = JpaUtil_22145150.getEntityManager();
        try { return em.find(Users_22145150.class, username); }
        finally { em.close(); }
    }
    @Override
    public void insert(Users_22145150 u) {
        JpaUtil_22145150.runInTransaction(em -> em.persist(u));
    }
}
