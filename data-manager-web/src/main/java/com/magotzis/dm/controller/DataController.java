package com.magotzis.dm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.magotzis.dm.api.service.DataManagementApiService;
import com.magotzis.dm.enums.DataHistoryType;
import com.magotzis.dm.model.DataHistory;
import com.magotzis.dm.model.User;
import com.magotzis.dm.service.DataHistoryService;
import com.magotzis.dm.service.UserService;
import com.magotzis.dm.util.ResultMap;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dengyq on 15:15 2018/4/10
 */
@RestController
@RequestMapping("/data")
public class DataController {

    @Resource
    private DataHistoryService dataHistoryService;

    @Resource
    private UserService userService;

    @Reference
    private DataManagementApiService dataManagementApiService;

    @GetMapping("/importHistory")
    public ResultMap getImportHistoryList() {
        return new ResultMap().success(dataHistoryService.getList(DataHistoryType.IMPORT.getType()));
    }

    @GetMapping("/importHistory/{id}")
    public ResultMap getImportHistoryById(@PathVariable("id") int id) {
        return new ResultMap().success(dataHistoryService.getById(id, DataHistoryType.IMPORT.getType()));
    }

    @PostMapping("/importHistory")
    public ResultMap addImportHistory(HttpServletRequest request, @RequestBody() DataHistory dataHistory) {
        Assert.hasText(dataHistory.getContent(), "content can not be null");
        Assert.hasText(dataHistory.getApplyContent(), "applyContent can not be null");
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession()
                .getAttribute("SPRING_SECURITY_CONTEXT");
        String username = securityContextImpl.getAuthentication().getName();
        User user = userService.getUserByUsername(username);
        dataHistory.setUserId(user.getId());
        dataHistory.setType(DataHistoryType.IMPORT.getType());
        dataHistoryService.importData(dataHistory);
        return new ResultMap().success();
    }

    @GetMapping("/exportHistory")
    public ResultMap getExportHistoryList() {
        return new ResultMap().success(dataHistoryService.getList(DataHistoryType.EXPORT.getType()));
    }

    @GetMapping("/exportHistory/{id}")
    public ResultMap getExportHistoryById(@PathVariable("id") int id) {
        return new ResultMap().success(dataHistoryService.getById(id, DataHistoryType.EXPORT.getType()));
    }

    @PostMapping("/exportHistory")
    public void addExportHistory(DataHistory dataHistory, HttpServletRequest request, HttpServletResponse response) {
        Assert.hasText(dataHistory.getContent(), "content can not be null");
        Assert.hasText(dataHistory.getApplyContent(), "applyContent can not be null");
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession()
                .getAttribute("SPRING_SECURITY_CONTEXT");
        String username = securityContextImpl.getAuthentication().getName();
        User user = userService.getUserByUsername(username);
        dataHistory.setUserId(user.getId());
        dataHistory.setType(DataHistoryType.EXPORT.getType());
        dataHistoryService.exportData(dataHistory, response);
    }

    @GetMapping("/dataSources")
    public ResultMap getDataSourceList() {
        return new ResultMap().success(dataManagementApiService.getDataSourceList());
    }
}
