package com.huyanh.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import com.huyanh.dao.IOrderItemDao_22145150;
import com.huyanh.entity.OrderItem_22145150;
import com.huyanh.util.JpaUtil_22145150;

public class OrderItemDaoImpl_22145150 implements IOrderItemDao_22145150 {

    @Override
    public void insert(OrderItem_22145150 item) {
        JpaUtil_22145150.runInTransaction(em -> em.persist(item));
    }

    @Override
    public List<OrderItem_22145150> findByOrder(int orderId) {
        EntityManager em = JpaUtil_22145150.getEntityManager();
        try {
            return em.createQuery(
                    "select i from OrderItems i where i.order.orderId = :oid", OrderItem_22145150.class)
                    .setParameter("oid", orderId).getResultList();
        } finally { em.close(); }
    }
}
