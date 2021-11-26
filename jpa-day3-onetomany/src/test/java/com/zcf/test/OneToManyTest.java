package com.zcf.test;

import com.zcf.dao.CustomerDao;
import com.zcf.dao.LinkManDao;
import com.zcf.domain.Customer;
import com.zcf.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustName("baidu");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("zhangsan");

        customer.getLinkMans().add(linkMan);
        linkMan.setCustomer(customer);

        customerDao.save(customer);
        linkManDao.save(linkMan);

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testCascadeSave() {
        Customer customer = new Customer();
        customer.setCustName("baidu");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("zhangsan");

        customer.getLinkMans().add(linkMan);
        linkMan.setCustomer(customer);

        customerDao.save(customer);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testCascadeDelete() {
        Customer customer = customerDao.findOne(1L);
        customerDao.delete(customer);
    }
}
