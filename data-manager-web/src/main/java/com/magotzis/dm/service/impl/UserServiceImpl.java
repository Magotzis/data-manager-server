package com.magotzis.dm.service.impl;

import com.magotzis.dm.dao.UserDao;
import com.magotzis.dm.model.User;
import com.magotzis.dm.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dengyq on 16:30 2018/1/12
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    @Cacheable(value = "user")
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public User getUserAndRolesByUsername(String username) {
        return userDao.getUserAndRolesByUsername(username);
    }
}
