package com.magotzis.dm.controller;

import com.magotzis.dm.model.Resource;
import com.magotzis.dm.model.Role;
import com.magotzis.dm.service.ResourceService;
import com.magotzis.dm.service.RoleService;
import com.magotzis.dm.util.ResultMap;
import com.magotzis.dm.vo.RoleResourcesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dengyq on 10:16 2018/3/27
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    private ResourceService resourceService;

    @Autowired
    public RoleController(RoleService roleService, ResourceService resourceService) {
        this.roleService = roleService;
        this.resourceService = resourceService;
    }

    @GetMapping
    public ResultMap getRolesList() {
        return new ResultMap().success(roleService.getAll());
    }

    @PostMapping
    public ResultMap addRole(@RequestBody() RoleResourcesVo roleResourcesVo) {
        Assert.isTrue(roleResourcesVo != null, "roleResourcesVo can't be null");
        Assert.isTrue(!StringUtils.isEmpty(roleResourcesVo.getRole()), "roleName can't be null");
        roleService.addRole(roleResourcesVo.getRole(), roleResourcesVo.getResources());
        return new ResultMap().success();
    }

    @DeleteMapping("/{id}")
    public ResultMap deleteRole(@PathVariable("id") int id) {
        roleService.deleteRole(id);
        return new ResultMap().success();
    }

    @PutMapping("/{id}/resources")
    public ResultMap updateRoleResources(@PathVariable("id") int id, @RequestBody List<Resource> resources) {
        roleService.updateRoleResources(id, resources);
        return new ResultMap().success();
    }

    @GetMapping("/{id}/resources")
    public ResultMap getRoleResources(@PathVariable("id") int id) {
        Role role = roleService.getRoleAndResourcesById(id);
        return new ResultMap().success(role.getResources());
    }

    @GetMapping("/resources")
    public ResultMap getAllResources() {
        return new ResultMap().success(resourceService.listResourceWithRoles());
    }
}
