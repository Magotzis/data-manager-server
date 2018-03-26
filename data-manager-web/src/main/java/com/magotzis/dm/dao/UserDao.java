package com.magotzis.dm.dao;

import com.magotzis.dm.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dengyq on 16:31 2018/1/12
 */
@Repository
public interface UserDao {

    List<User> listUsers();

    User getUserByUsername(@Param("username") String username);

    User getUserAndRolesByUsername(@Param("username") String username);

    void deleteUser(@Param("username") String username);

    User getActiveUserByUsername(@Param("username") String username);
}
