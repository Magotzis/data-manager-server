package com.magotzis.dm.api.service;

import com.magotzis.dm.api.dto.AnalysisDto;

import java.util.List;

/**
 * @author dengyq on 17:31 2018/4/17
 */
public interface AnalysisApiService {
    List<AnalysisDto> getFullDataSourcesAnalysis();

    int getDataSourceNum(String dataSource, int type, String time);
}
