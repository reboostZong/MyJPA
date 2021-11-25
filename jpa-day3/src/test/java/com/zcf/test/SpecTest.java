package com.zcf.test;

import com.zcf.dao.CustomerDao;
import com.zcf.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindOne() {
        // specification构造查询条件
        Specification<Customer> spec =  new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate predicate = cb.equal(custName, "zcf");
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    @Test
    public void testFindOne2() {
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                Predicate p1 = cb.equal(custName, "zcf");
                Predicate p2 = cb.equal(custIndustry, "IT");
                Predicate predicate = cb.or(p1, p2);

                return predicate;
            }
        };


        List<Customer> list = customerDao.findAll(spec);
        for (Customer customer : list) {
            System.out.println(customer);

        }

    }

    @Test
    public void testFindLike() {
        Specification<Customer> spec= new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate predicate = cb.like(custName.as(String.class), "zcf%");

                return predicate;
            }
        };

        List<Customer> list = customerDao.findAll(spec);
        for (Customer customer : list) {
            System.out.println(customer);

        }
    }

    @Test
    public void testFindOrder() {
        Specification<Customer> spec= new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate predicate = cb.like(custName.as(String.class), "zcf%");

                return predicate;
            }
        };

        Sort sort = new Sort(Sort.Direction.DESC, "custId");
        List<Customer> list = customerDao.findAll(spec, sort);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    @Test
    public void testFindPage() {
        Specification spec = null;

        Pageable pageable = new PageRequest(2, 2);
        Page page = customerDao.findAll(spec, pageable);

        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        System.out.println(page.getSize());
        System.out.println(page.getNumber());
        System.out.println(page.getContent());
    }
}
