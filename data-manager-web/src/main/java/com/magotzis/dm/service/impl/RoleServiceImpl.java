package com.magotzis.dm.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.magotzis.dm.dao.ResourceDao;
import com.magotzis.dm.dao.RoleDao;
import com.magotzis.dm.exception.auth.RoleNotExistException;
import com.magotzis.dm.model.Resource;
import com.magotzis.dm.model.Role;
import com.magotzis.dm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dengyq on 11:41 2018/1/18
 */
@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    private ResourceDao resourceDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao, ResourceDao resourceDao) {
        this.roleDao = roleDao;
        this.resourceDao = resourceDao;
    }

    @Override
    public List<Role> getAll() {
        return roleDao.findAll();
    }

    @Override
    public Role getRoleById(int id) {
        return roleDao.getById(id);
    }

    @Override
    public void deleteRole(int id) {
        Role role = getRoleById(id);
        if (role == null) {
            throw new RoleNotExistException();
        }
        roleDao.deleteRole(id);
    }

    @Override
    public void updateRoleResources(int id, List<Resource> resources) {
        Role role = getRoleById(id);
        if (role == null) {
            throw new RoleNotExistException();
        }
        List<Resource> hasResources = resourceDao.findResourceByRoleId(role.getId());
        List<Resource> deleteResources = hasResources.stream().filter(r -> !resources.contains(r)).collect(Collectors.toList());
        List<Resource> addResources = resources.stream().filter(r -> !hasResources.contains(r)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(deleteResources)) {
            resourceDao.deleteResources(role.getId(), deleteResources);
        }
        if (CollectionUtils.isNotEmpty(addResources)) {
            resourceDao.addRoleResources(role.getId(), addResources);
        }
    }

    @Override
    public Role getRoleAndResourcesById(int id) {
        return roleDao.findRoleAndResourcesById(id);
    }

    @Override
    public void addRole(String roleName, List<Resource> resources) {
        Role role = new Role();
        role.setRole(roleName);
        roleDao.addRole(role);
        if (CollectionUtils.isNotEmpty(resources)) {
            resourceDao.addRoleResources(role.getId(), resources);
        }
    }
}
