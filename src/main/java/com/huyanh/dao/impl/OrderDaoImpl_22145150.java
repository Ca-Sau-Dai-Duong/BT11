package com.huyanh.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import com.huyanh.dao.IOrderDao_22145150;
import com.huyanh.entity.Order_22145150;
import com.huyanh.entity.OrderStatus_22145150;
import com.huyanh.util.JpaUtil_22145150;

public class OrderDaoImpl_22145150 implements IOrderDao_22145150 {

    @Override
    public void insert(Order_22145150 order) {
        JpaUtil_22145150.runInTransaction(em -> em.persist(order));
    }

    @Override
    public Order_22145150 findById(int orderId) {
        EntityManager em = JpaUtil_22145150.getEntityManager();
        try { return em.find(Order_22145150.class, orderId); }
        finally { em.close(); }
    }

    @Override
    public List<Order_22145150> findByUser(String username, OrderStatus_22145150 status) {
        EntityManager em = JpaUtil_22145150.getEntityManager();
        try {
            String jpql = "select o from Orders o where o.user.username = :u"
                    + (status != null ? " and o.status = :s" : "")
                    + " order by o.orderDate desc";
            javax.persistence.TypedQuery<Order_22145150> q = em.createQuery(jpql, Order_22145150.class)
                    .setParameter("u", username);
            if (status != null) q.setParameter("s", status);
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public OrderStatus_22145150 findStatus(int orderId) {
        // Dùng SQL thuần, KHÔNG qua cache của EntityManager, để luôn đọc đúng giá trị mới nhất trong DB
        // (phục vụ yêu cầu: đổi trạng thái trực tiếp trong SQL rồi xem web cập nhật theo)
        EntityManager em = JpaUtil_22145150.getEntityManager();
        try {
            Object result = em.createNativeQuery("SELECT Status FROM Orders WHERE OrderId = :id")
                    .setParameter("id", orderId).getSingleResult();
            return OrderStatus_22145150.valueOf((String) result);
        } finally { em.close(); }
    }
}
