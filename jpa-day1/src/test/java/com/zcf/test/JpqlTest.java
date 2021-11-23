package com.zcf.test;

import com.zcf.util.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class JpqlTest {

    @Test
    public void testFindAll() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);
        List resultList = query.getResultList();
        for (Object customer : resultList) {
            System.out.println(customer);
        }

        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testOrder() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        String jpql = "from Customer order by custName desc";
        Query query = entityManager.createQuery(jpql);
        List resultList = query.getResultList();
        for (Object customer : resultList) {
            System.out.println(customer);
        }

        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testCount() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        String jpql = "select count(*) from Customer ";
        Query query = entityManager.createQuery(jpql);
        Object singleResult = query.getSingleResult();
        System.out.println(singleResult);

        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testPage() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //  limit ?, ?
        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);
        query.setFirstResult(0);
        query.setMaxResults(2);

        List resultList = query.getResultList();
        for (Object customer : resultList) {
            System.out.println(customer);
        }

        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testWhere() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        String jpql = "from Customer where custName like ?";
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1, "zcf%");

        List resultList = query.getResultList();
        for (Object customer : resultList) {
            System.out.println(customer);
        }

        transaction.commit();
        entityManager.close();
    }
}
