package com.zcf.test;

import com.zcf.dao.CustomerDao;
import com.zcf.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
