package com.zcf.test;

import com.zcf.dao.RoleDao;
import com.zcf.dao.UserDao;
import com.zcf.domain.Role;
import com.zcf.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Test
    @Transactional
    @Rollback(false)
    public void testManySave() {
        User user = new User();
        user.setUserName("zhangsan");

        Role role = new Role();
        role.setRoleName("admin");

        user.getRoles().add(role);
        role.getUsers().add(user);

        userDao.save(user);
        roleDao.save(role);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeSave() {
        User user = new User();
        user.setUserName("zhangsan");

        Role role = new Role();
        role.setRoleName("admin");

        user.getRoles().add(role);
        role.getUsers().add(user);

        userDao.save(user);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeDelete() {
        User user = userDao.findOne(1L);

        userDao.delete(user);

    }

}
