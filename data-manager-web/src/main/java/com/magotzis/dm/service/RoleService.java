package com.magotzis.dm.service;

import com.magotzis.dm.model.Resource;
import com.magotzis.dm.model.Role;

import java.util.List;

/**
 * @author dengyq on 11:37 2018/1/18
 */
public interface RoleService {
    List<Role> getAll();

    Role getRoleById(int id);

    void deleteRole(int id);

    void updateRoleResources(int id, List<Resource> resources);

    Role getRoleAndResourcesById(int id);

    void addRole(String roleName, List<Resource> resources);
}
