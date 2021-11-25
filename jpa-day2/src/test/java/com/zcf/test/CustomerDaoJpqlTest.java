package com.zcf.test;

import com.zcf.dao.CustomerDao;
import com.zcf.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoJpqlTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindJpql() {
        Customer customer = customerDao.findJpql("zhangsan");
        System.out.println(customer);

    }

    @Test
    public void testFindCustNameAndId() {
        Customer customer = customerDao.findCustNameAndId(8L, "zhangsan");
        System.out.println(customer);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdateCustName() {
        customerDao.updateCustName(8L, "zhangsan modify");

    }

    @Test
    public void testFindSql() {
        List<Object[]> list = customerDao.findSql();
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testFindSqlLikeName() {
        List<Object[]> list = customerDao.findSqlLikeName("zhangsan%");
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testFindByCustName() {
        Customer customer = customerDao.findByCustName("zcf3");
        System.out.println(customer);
    }

    @Test
    public void testFindByCustNameLike() {
        List<Customer> list = customerDao.findByCustNameLike("zcf%");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    @Test
    public void testFindByCustNameLikeAndCustIndustry() {
        List<Customer> list = customerDao.findByCustNameLikeAndCustIndustry("zcf%", "IT");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
}
