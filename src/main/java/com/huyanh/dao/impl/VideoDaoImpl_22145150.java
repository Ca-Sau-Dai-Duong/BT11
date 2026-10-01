package com.huyanh.dao.impl;

import com.huyanh.dao.IVideoDao_22145150;
import java.util.List;
import javax.persistence.EntityManager;
import com.huyanh.entity.Videos_22145150;
import com.huyanh.util.JpaUtil_22145150;

public class VideoDaoImpl_22145150 implements IVideoDao_22145150 {

    /** Gán số Share / Like cho video */
    private void fill(EntityManager em, Videos_22145150 v) {
        v.setShareCount(em.createQuery("select count(s) from Shares s where s.video.videoId = :id", Long.class)
                .setParameter("id", v.getVideoId()).getSingleResult());
        v.setLikeCount(em.createQuery("select count(f) from Favorites f where f.video.videoId = :id", Long.class)
                .setParameter("id", v.getVideoId()).getSingleResult());
    }
    private void fill(EntityManager em, List<Videos_22145150> list) {
        for (Videos_22145150 v : list) fill(em, v);
    }

    @Override
    public List<Videos_22145150> findPage(int page, int size, boolean onlyActive) {
        EntityManager em = JpaUtil_22145150.getEntityManager();
        try {
            String jpql = "select v from Videos v " + (onlyActive ? "where v.active = true " : "") + "order by v.videoId";
            List<Videos_22145150> list = em.createQuery(jpql, Videos_22145150.class)
                    .setFirstResult((page - 1) * size).setMaxResults(size).getResultList();
            fill(em, list);
            return list;
        } finally { em.close(); }
    }

    @Override
    public long count(boolean onlyActive) {
        EntityManager em = JpaUtil_22145150.getEntityManager();
        try {
            String jpql = "select count(v) from Videos v " + (onlyActive ? "where v.active = true" : "");
            return em.createQuery(jpql, Long.class).getSingleResult();
        } finally { em.close(); }
    }

    @Override
    public List<Videos_22145150> findByCategory(int categoryId, int page, int size) {
        EntityManager em = JpaUtil_22145150.getEntityManager();
        try {
            List<Videos_22145150> list = em.createQuery(
                    "select v from Videos v where v.category.categoryId = :cid and v.active = true order by v.videoId",
                    Videos_22145150.class)
                    .setParameter("cid", categoryId)
                    .setFirstResult((page - 1) * size).setMaxResults(size).getResultList();
            fill(em, list);
            return list;
        } finally { em.close(); }
    }

    /** Câu 5: đếm số video theo category */
    @Override
    public long countByCategory(int categoryId) {
        EntityManager em = JpaUtil_22145150.getEntityManager();
        try {
            return em.createQuery("select count(v) from Videos v where v.category.categoryId = :cid and v.active = true", Long.class)
                    .setParameter("cid", categoryId).getSingleResult();
        } finally { em.close(); }
    }

    @Override
    public Videos_22145150 findById(String id) {
        EntityManager em = JpaUtil_22145150.getEntityManager();
        try {
            Videos_22145150 v = em.find(Videos_22145150.class, id);
            if (v != null) fill(em, v);
            return v;
        } finally { em.close(); }
    }

    @Override
    public void insert(Videos_22145150 v) {
        JpaUtil_22145150.runInTransaction(em -> em.persist(v));
    }

    @Override
    public void update(Videos_22145150 v) {
        JpaUtil_22145150.runInTransaction(em -> em.merge(v));
    }

    @Override
    public void delete(String id) {
        JpaUtil_22145150.runInTransaction(em -> {
            em.createQuery("delete from Shares s where s.video.videoId = :id").setParameter("id", id).executeUpdate();
            em.createQuery("delete from Favorites f where f.video.videoId = :id").setParameter("id", id).executeUpdate();
            Videos_22145150 v = em.find(Videos_22145150.class, id);
            if (v != null) em.remove(v);
        });
    }
}
