package com.magotzis.dm.dao;

import com.magotzis.dm.api.dto.AnalysisDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dengyq on 17:41 2018/4/17
 */
@Repository
public interface AnalysisDao {

    List<AnalysisDto> getFullDataSourcesAnalysis();
}
