package com.magotzis.dm.controller;

import com.magotzis.dm.service.AnalysisService;
import com.magotzis.dm.util.ResultMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dengyq on 17:54 2018/4/17
 */
@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    @Resource
    private AnalysisService analysisService;

    @GetMapping("/dataSources")
    public ResultMap getFullDataSourcesAnalysis() {
        return new ResultMap().success(analysisService.getFullDataSourcesAnalysis());
    }

    @GetMapping("/dataSources/{dataSources}")
    public ResultMap getDataSourcesAnalysis(@PathVariable("dataSources") String dataSources, String begin, String end) {
        Assert.hasText(dataSources, "dataSources can not be null");
        Assert.hasText(begin, "begin can not be null");
        Assert.hasText(end, "end can not be null");
        return new ResultMap().success(analysisService.getDataSourcesAnalysis(dataSources, begin, end));
    }

    @GetMapping("/userRecord/{username}")
    public ResultMap getUserRecordAnalysis(@PathVariable("username") String username, String begin, String end) {
        Assert.hasText(username, "dataSources can not be null");
        Assert.hasText(begin, "begin can not be null");
        Assert.hasText(end, "end can not be null");
        return new ResultMap().success(analysisService.getUserRecordAnalysis(username, begin, end));
    }
}
