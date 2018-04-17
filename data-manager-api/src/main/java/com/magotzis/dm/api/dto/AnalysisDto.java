package com.magotzis.dm.api.dto;

import java.io.Serializable;

/**
 * @author dengyq on 17:32 2018/4/17
 */
public class AnalysisDto implements Serializable {

    private String name;

    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AnalysisDto{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
