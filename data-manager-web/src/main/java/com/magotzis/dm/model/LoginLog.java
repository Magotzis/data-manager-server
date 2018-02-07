package com.magotzis.dm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dengyq on 9:39 2018/2/1
 */
public class LoginLog implements Serializable {

    private int id;
    private int userId;
    private String ip;
    private Date createDt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    @Override
    public String toString() {
        return "LoginLog{" + "id=" + id +
                ", userId=" + userId +
                ", ip='" + ip + '\'' +
                ", createDt=" + createDt +
                '}';
    }
}
