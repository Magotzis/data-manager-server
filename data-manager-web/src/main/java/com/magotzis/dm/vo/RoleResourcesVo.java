package com.magotzis.dm.vo;

import com.magotzis.dm.model.Resource;

import java.util.List;

/**
 * @author dengyq on 14:53 2018/3/28
 */
public class RoleResourcesVo {

    private String role;

    private List<Resource> resources;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "RoleResourcesVo{" +
                "role='" + role + '\'' +
                ", resources=" + resources +
                '}';
    }
}
