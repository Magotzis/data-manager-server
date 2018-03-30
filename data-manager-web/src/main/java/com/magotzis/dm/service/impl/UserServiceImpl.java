package com.magotzis.dm.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.magotzis.dm.dao.RoleDao;
import com.magotzis.dm.dao.UserDao;
import com.magotzis.dm.exception.auth.RepasswordNotEqualsException;
import com.magotzis.dm.exception.user.EmailExistException;
import com.magotzis.dm.exception.user.UserNotExistException;
import com.magotzis.dm.exception.user.UsernameExistException;
import com.magotzis.dm.model.Role;
import com.magotzis.dm.model.User;
import com.magotzis.dm.service.UserService;
import com.magotzis.dm.vo.UserRegisterVo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
    public void registerUser(UserRegisterVo registerVo) {
        if (!registerVo.getPassword().equals(registerVo.getRpassword())) {
            throw new RepasswordNotEqualsException();
        }
        User user = getUserByUsername(registerVo.getUsername());
        if (user != null) {
            throw new UsernameExistException();
        }
        user = userDao.findUserByEmail(registerVo.getEmail());
        if (user != null) {
            throw new EmailExistException();
        }
        user = new User();
        user.setUsername(registerVo.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(registerVo.getPassword()));
        user.setEmail(registerVo.getEmail());
        user.setFullname(registerVo.getFullname());
        user.setCreateTime(new Date());
        userDao.add(user);
    }

    @Override
    public User getUserAndRolesByUsername(String username) {
        return userDao.getUserAndRolesByUsername(username);
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
            roleDao.addUserRoles(user.getId(), addRoles);
        }
    }
}
