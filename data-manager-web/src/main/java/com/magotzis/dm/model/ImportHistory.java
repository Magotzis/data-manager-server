package com.magotzis.dm.model;

import java.util.Date;

/**
 * @author dengyq on 10:33 2018/4/10
 */
public class ImportHistory {

    private int id;

    private int userId;

    private String applyContent;

    private int state;

    private String errorMsg;

    private String content;

    private Date updateTime;

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

    public String getApplyContent() {
        return applyContent;
    }

    public void setApplyContent(String applyContent) {
        this.applyContent = applyContent;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ImportHistory{" +
                "id=" + id +
                ", userId=" + userId +
                ", applyContent='" + applyContent + '\'' +
                ", state=" + state +
                ", errorMsg='" + errorMsg + '\'' +
                ", content='" + content + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
