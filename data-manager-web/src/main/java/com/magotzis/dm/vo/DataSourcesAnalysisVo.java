package com.magotzis.dm.vo;

import java.util.List;

/**
 * @author dengyq on 20:55 2018/4/20
 */
public class DataSourcesAnalysisVo {

    private List<String> Axis;

    private List<AnalysisVo> analysisVos;

    public List<String> getAxis() {
        return Axis;
    }

    public void setAxis(List<String> axis) {
        Axis = axis;
    }

    public List<AnalysisVo> getAnalysisVos() {
        return analysisVos;
    }

    public void setAnalysisVos(List<AnalysisVo> analysisVos) {
        this.analysisVos = analysisVos;
    }

    @Override
    public String toString() {
        return "DataSourcesAnalysisVo{" +
                "Axis=" + Axis +
                ", analysisVos=" + analysisVos +
                '}';
    }
}
