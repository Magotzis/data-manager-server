package com.magotzis.dm.dao;

import com.magotzis.dm.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author dengyq on 16:31 2018/1/12
 */
@Repository
public interface UserDao {

    List<User> listUsers();

    User getUserByUsername(@PathVariable("username") String username);

    User getUserAndRolesByUsername(@PathVariable("username") String username);
}
