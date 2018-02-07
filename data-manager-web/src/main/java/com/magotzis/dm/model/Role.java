package com.magotzis.dm.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author dengyq on 10:44 2018/1/16
 */
public class Role implements Serializable {

    private int id;

    private String role;

    private List<Resource> resources;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("id=").append(id);
        sb.append(", role='").append(role).append('\'');
        sb.append(", resources=").append(resources);
        sb.append('}');
        return sb.toString();
    }
}
