package com.zcf.test;

import com.zcf.domain.Customer;
import com.zcf.util.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

    @Test
    public void testSave() {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myJpa");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Customer customer = new Customer();
        customer.setCustName("zcf");
        customer.setCustIndustry("IT");

        entityManager.persist(customer);

        transaction.commit();
        entityManager.close();
//        entityManagerFactory.close();
    }

    @Test
    public void testFind() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Customer customer = entityManager.find(Customer.class, 1L);
        System.out.println(customer);

        transaction.commit();
        entityManager.close();
    }


    @Test
    public void testGetReference() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Customer reference = entityManager.getReference(Customer.class, 1L);
        System.out.println(reference);

        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testDelete() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Customer customer = entityManager.find(Customer.class, 1L);
        entityManager.remove(customer);

        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testUpdate() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Customer customer = entityManager.find(Customer.class, 2L);
        customer.setCustId(2L);
        customer.setCustIndustry("IT");

        entityManager.merge(customer);

        transaction.commit();
        entityManager.close();

    }
}
