package com.magotzis.dm.service.impl;

import com.magotzis.dm.api.dto.AnalysisDto;
import com.magotzis.dm.dao.AnalysisDao;
import com.magotzis.dm.service.AnalysisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dengyq on 17:39 2018/4/17
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Resource
    private AnalysisDao analysisDao;

    @Override
    public List<AnalysisDto> getFullDataSourcesAnalysis() {
        return analysisDao.getFullDataSourcesAnalysis();
    }

    @Override
    public int getDataSourceNum(String dataSource, int type, String time) {
        return analysisDao.getDataSourceNum(dataSource, type, time);
    }
}
