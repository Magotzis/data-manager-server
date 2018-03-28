package com.magotzis.dm.dao;

import com.magotzis.dm.model.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dengyq on 10:44 2018/1/18
 */
@Repository
public interface ResourceDao {

    List<Resource> listResourceWithRoles();

    List<Resource> findResourceByRoleId(@Param("roleId") int roleId);

    void deleteResources(@Param("roleId") int roleId,@Param("resources") List<Resource> deleteResources);

    void addRoleResources(@Param("roleId") int roleId,@Param("resources") List<Resource> addResources);
}
