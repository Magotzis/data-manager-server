package com.magotzis.dm.vo;

import java.util.List;

/**
 * @author dengyq on 21:01 2018/4/20
 */
public class AnalysisVo {

    private String name;

    private List<Integer> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getList() {
        return data;
    }

    public void setList(List<Integer> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AnalysisVo{" +
                "name='" + name + '\'' +
                ", data=" + data +
                '}';
    }
}
