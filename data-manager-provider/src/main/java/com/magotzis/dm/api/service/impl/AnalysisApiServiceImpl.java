package com.magotzis.dm.api.service.impl;

import com.magotzis.dm.api.dto.AnalysisDto;
import com.magotzis.dm.api.service.AnalysisApiService;
import com.magotzis.dm.service.AnalysisService;
import com.alibaba.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dengyq on 17:36 2018/4/17
 */
@Service
public class AnalysisApiServiceImpl implements AnalysisApiService {

    @Resource
    private AnalysisService analysisService;

    @Override
    public List<AnalysisDto> getFullDataSourcesAnalysis() {
        return analysisService.getFullDataSourcesAnalysis();
    }
}
