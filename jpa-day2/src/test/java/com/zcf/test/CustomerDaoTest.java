package com.zcf.test;

import com.zcf.dao.CustomerDao;
import com.zcf.domain.Customer;
import javafx.geometry.VPos;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindOne() {
        Customer customer = customerDao.findOne(3L);
        System.out.println(customer);
    }

    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustName("zhangsan");
        customer.setCustLevel("vip");
        customer.setCustIndustry("IT");

        customerDao.save(customer);
    }

    @Test
    public void testSaveNew() {
        Customer customer = new Customer();
        customer.setCustId(70L);
        customer.setCustName("zhangsan");
        customer.setCustLevel("vip");
        customer.setCustIndustry("IT");

        // 并没有根据设置的id新增，而且其他id
        customerDao.save(customer);
    }

    @Test
    public void testUpdate() {
        Customer customer = new Customer();
        customer.setCustId(5L);
        customer.setCustName("zhangsan is good");

        // 全量更新

        customerDao.save(customer);
    }
    @Test
    public void testUpdateGood() {
        Customer one = customerDao.findOne(6L);
        one.setCustName("zhangsan is good");

        // 全量更新
        customerDao.save(one);
    }

    @Test
    public void testDelete() {
        customerDao.delete(7L);
    }

    @Test
    public void testFindAll() {
        List<Customer> customerList = customerDao.findAll();
        for (Customer customer : customerList) {
            System.out.println(customer);

        }
    }

    @Test
    public void testCount() {
        long count = customerDao.count();
        System.out.println(count);
    }

    @Test
    public void testExist() {
        boolean exists = customerDao.exists(6L);
        System.out.println(exists);
    }

    /**
     * getReference
     */
    @Test
    @Transactional
    public void testGetOne() {
        Customer one = customerDao.getOne(6L);
        System.out.println(one);
    }



}
