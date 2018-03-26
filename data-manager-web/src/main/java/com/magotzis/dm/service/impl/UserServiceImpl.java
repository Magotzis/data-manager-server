package com.magotzis.dm.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.magotzis.dm.component.pageHelper.Page;
import com.magotzis.dm.component.pageHelper.PageCallBack;
import com.magotzis.dm.component.pageHelper.PageCallBackUtil;
import com.magotzis.dm.component.pageHelper.PageQuery;
import com.magotzis.dm.dao.RoleDao;
import com.magotzis.dm.dao.UserDao;
import com.magotzis.dm.exception.user.UserNotExistException;
import com.magotzis.dm.model.Role;
import com.magotzis.dm.model.User;
import com.magotzis.dm.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dengyq on 16:30 2018/1/12
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public User getActiveUserByUsername(String username) {
        return userDao.getActiveUserByUsername(username);
    }

    @Override
    public User getUserAndRolesByUsername(String username) {
        return userDao.getUserAndRolesByUsername(username);
    }

    @Override
    public Page<User> getUserPage(PageQuery pageQuery) {
        return PageCallBackUtil.selectPage(pageQuery, new PageCallBack() {
            @Override
            public List<User> select() {
                return userDao.listUsers();
            }
        });
    }

    @Override
    public void deleteUser(String username) {
        User user = getActiveUserByUsername(username);
        if (user == null) {
            throw new UserNotExistException();
        }
        userDao.deleteUser(username);
    }

    @Override
    public void updateUserRoles(String username, List<Role> roles) {
        User user = getActiveUserByUsername(username);
        if (user == null) {
            throw new UserNotExistException();
        }
        List<Role> hasRoles = roleDao.getRolesByUserId(user.getId());
        List<Role> deleteRoles = hasRoles.stream().filter(role -> !roles.contains(role)).collect(Collectors.toList());
        List<Role> addRoles = roles.stream().filter(role -> !hasRoles.contains(role)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(deleteRoles)) {
            roleDao.deleteRoles(user.getId(), deleteRoles);
        }
        if (CollectionUtils.isNotEmpty(addRoles)) {
            roleDao.addRoles(user.getId(), addRoles);
        }
    }
}
