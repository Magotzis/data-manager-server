package com.magotzis.dm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.magotzis.dm.api.service.AnalysisApiService;
import com.magotzis.dm.util.ResultMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengyq on 17:54 2018/4/17
 */
@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    @Reference
    private AnalysisApiService analysisApiService;

    @GetMapping("/dataSources")
    public ResultMap getFullDataSourcesAnalysis() {
        return new ResultMap().success(analysisApiService.getFullDataSourcesAnalysis());
    }
}
