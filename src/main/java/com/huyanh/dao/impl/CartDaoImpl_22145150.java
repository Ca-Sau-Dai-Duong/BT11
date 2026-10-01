package com.huyanh.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import com.huyanh.dao.ICartDao_22145150;
import com.huyanh.entity.CartItem_22145150;
import com.huyanh.util.JpaUtil_22145150;

public class CartDaoImpl_22145150 implements ICartDao_22145150 {

    @Override
    public List<CartItem_22145150> findByUser(String username) {
        EntityManager em = JpaUtil_22145150.getEntityManager();
        try {
            return em.createQuery(
                    "select c from CartItems c where c.user.username = :u order by c.cartItemId",
                    CartItem_22145150.class)
                    .setParameter("u", username).getResultList();
        } finally { em.close(); }
    }

    @Override
    public CartItem_22145150 findByUserAndVideo(String username, String videoId) {
        EntityManager em = JpaUtil_22145150.getEntityManager();
        try {
            List<CartItem_22145150> list = em.createQuery(
                    "select c from CartItems c where c.user.username = :u and c.video.videoId = :v",
                    CartItem_22145150.class)
                    .setParameter("u", username).setParameter("v", videoId)
                    .getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally { em.close(); }
    }

    @Override
    public CartItem_22145150 findById(int cartItemId) {
        EntityManager em = JpaUtil_22145150.getEntityManager();
        try { return em.find(CartItem_22145150.class, cartItemId); }
        finally { em.close(); }
    }

    @Override
    public void insert(CartItem_22145150 item) {
        JpaUtil_22145150.runInTransaction(em -> em.persist(item));
    }

    @Override
    public void update(CartItem_22145150 item) {
        JpaUtil_22145150.runInTransaction(em -> em.merge(item));
    }

    @Override
    public void delete(int cartItemId) {
        JpaUtil_22145150.runInTransaction(em -> {
            CartItem_22145150 it = em.find(CartItem_22145150.class, cartItemId);
            if (it != null) em.remove(it);
        });
    }

    @Override
    public void deleteAllByUser(String username) {
        JpaUtil_22145150.runInTransaction(em ->
            em.createQuery("delete from CartItems c where c.user.username = :u")
              .setParameter("u", username).executeUpdate());
    }

    @Override
    public long countItems(String username) {
        EntityManager em = JpaUtil_22145150.getEntityManager();
        try {
            Long sum = em.createQuery(
                    "select coalesce(sum(c.quantity),0) from CartItems c where c.user.username = :u", Long.class)
                    .setParameter("u", username).getSingleResult();
            return sum;
        } finally { em.close(); }
    }
}
