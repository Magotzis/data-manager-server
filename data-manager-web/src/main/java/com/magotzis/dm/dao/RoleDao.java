package com.magotzis.dm.dao;

import com.magotzis.dm.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dengyq on 11:31 2018/1/16
 */
@Repository
public interface RoleDao {
    String getById(@Param("id") int id);

    List<Role> getRolesByUserId(@Param("userId") int userId);

    void deleteRoles(@Param("userId") int userId, @Param("roles") List<Role> roles);

    void addRoles(@Param("userId") int userId, @Param("roles") List<Role> roles);

    List<Role> findAll();
}
