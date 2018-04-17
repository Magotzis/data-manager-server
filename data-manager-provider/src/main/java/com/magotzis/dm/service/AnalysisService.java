package com.magotzis.dm.service;

import com.magotzis.dm.api.dto.AnalysisDto;

import java.util.List;

/**
 * @author dengyq on 17:38 2018/4/17
 */
public interface AnalysisService {
    public List<AnalysisDto> getFullDataSourcesAnalysis();
}
