package com.magotzis.dm.vo;

import java.util.Date;

/**
 * @author dengyq on 15:20 2018/4/10
 */
public class ImportHistoryVo {

    private int id;

    private String username;

    private int state;

    private String applyContent;

    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getApplyContent() {
        return applyContent;
    }

    public void setApplyContent(String applyContent) {
        this.applyContent = applyContent;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ImportHistoryVo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", state=" + state +
                ", applyContent='" + applyContent + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
