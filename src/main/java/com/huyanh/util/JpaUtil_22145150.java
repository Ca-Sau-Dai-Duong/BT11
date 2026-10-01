package com.huyanh.util;

import java.util.function.Consumer;
import javax.persistence.*;

public class JpaUtil_22145150 {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServletJpaPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /** Chạy 1 đoạn code trong transaction (tự commit / rollback / đóng EntityManager) */
    public static void runInTransaction(Consumer<EntityManager> work) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            work.accept(em);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
