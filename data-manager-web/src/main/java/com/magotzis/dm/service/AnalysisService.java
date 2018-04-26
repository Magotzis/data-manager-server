package com.magotzis.dm.service;

import com.magotzis.dm.api.dto.AnalysisDto;
import com.magotzis.dm.vo.DataSourcesAnalysisVo;

import java.util.List;

/**
 * @author dengyq on 17:47 2018/4/20
 */
public interface AnalysisService {
    List<AnalysisDto> getFullDataSourcesAnalysis();

    DataSourcesAnalysisVo getDataSourcesAnalysis(String dataSources, String begin, String end);

    DataSourcesAnalysisVo getUserRecordAnalysis(String username, String begin, String end);
}
