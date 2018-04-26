package com.magotzis.dm.api.service.impl;

import com.magotzis.dm.api.dto.AnalysisDto;
import com.magotzis.dm.api.service.AnalysisApiService;
import com.magotzis.dm.service.AnalysisService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.util.Assert;

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

    @Override
    public int getDataSourceNum(String dataSource, int type, String time) {
        Assert.hasText(dataSource, "dataSource can not be null");
        Assert.hasText(time, "time can not be null");
        return analysisService.getDataSourceNum(dataSource, type, time);
    }

    @Override
    public int getUserRecordNum(String username, int type, String time) {
        Assert.hasText(username, "username can not be null");
        Assert.hasText(time, "time can not be null");
        return analysisService.getUserRecordNum(username, type, time);
    }
}
