package com.magotzis.dm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author dengyq on 11:24 2018/1/17
 */
public class Resource implements Serializable {

    private int id;

    private String method;

    private String description;

    private List<Role> roles;

    private Date createTime;

    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Resource{");
        sb.append("id=").append(id);
        sb.append(", method='").append(method).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", roles=").append(roles);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append('}');
        return sb.toString();
    }
}
