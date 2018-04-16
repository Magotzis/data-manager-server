package com.magotzis.dm.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dengyq on 17:00 2018/4/16
 */
public class DataSourceDto implements Serializable {

    private String dataSource;

    private int rows;

    private Date createTime;

    private Date updateTime;

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "DataSourceDto{" +
                "dataSource='" + dataSource + '\'' +
                ", rows=" + rows +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
