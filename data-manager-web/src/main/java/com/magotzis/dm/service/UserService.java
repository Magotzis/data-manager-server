package com.magotzis.dm.service;

import com.magotzis.dm.model.Role;
import com.magotzis.dm.model.User;

import java.util.List;

/**
 * @author dengyq on 16:29 2018/1/12
 */
public interface UserService {

    List<User> listUsers();

    User getUserByUsername(String username);

    User getUserAndRolesByUsername(String username);

    void deleteUser(String username);

    void updateUserRoles(String username, List<Role> roles);

    User getActiveUserByUsername(String username);
}
