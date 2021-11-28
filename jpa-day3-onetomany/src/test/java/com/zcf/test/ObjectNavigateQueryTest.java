package com.zcf.test;

import com.zcf.dao.CustomerDao;
import com.zcf.dao.LinkManDao;
import com.zcf.domain.Customer;
import com.zcf.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectNavigateQueryTest {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional
    public void testGetOne() {
        Customer customer = customerDao.getOne(1L);
        Set<LinkMan> linkMans = customer.getLinkMans();
        System.out.println(linkMans.size());
    }


    @Test
    @Transactional
    public void testFindOne() {
        Customer customer = customerDao.findOne(1L);
        Set<LinkMan> linkMans = customer.getLinkMans();
        System.out.println(linkMans.size());
    }

    @Test
    @Transactional
    public void testLinkMan() {
        LinkMan linkMan = linkManDao.findOne(1L);
        Customer customer = linkMan.getCustomer();
        System.out.println(customer);
    }
}
