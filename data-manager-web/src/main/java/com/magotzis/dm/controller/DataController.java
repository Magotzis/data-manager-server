package com.magotzis.dm.controller;

import com.magotzis.dm.model.ImportHistory;
import com.magotzis.dm.model.User;
import com.magotzis.dm.service.ImportHistoryService;
import com.magotzis.dm.service.UserService;
import com.magotzis.dm.util.ResultMap;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author dengyq on 15:15 2018/4/10
 */
@RestController
@RequestMapping("/data")
public class DataController {

    @Resource
    private ImportHistoryService importHistoryService;

    @Resource
    private UserService userService;

    @GetMapping("/importHistory")
    public ResultMap getImportHistoryList() {
        return new ResultMap().success(importHistoryService.getList());
    }

    @GetMapping("/importHistory/{id}")
    public ResultMap getImportHistoryById(@PathVariable("id") int id) {
        return new ResultMap().success(importHistoryService.getById(id));
    }

    @PostMapping("/importHistory")
    public ResultMap addImportHistory(HttpServletRequest request, @RequestBody() ImportHistory importHistory) {
        Assert.hasText(importHistory.getContent(), "content can not be null");
        Assert.hasText(importHistory.getApplyContent(), "applyContent can not be null");
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession()
                .getAttribute("SPRING_SECURITY_CONTEXT");
        String username = securityContextImpl.getAuthentication().getName();
        User user = userService.getUserByUsername(username);
        importHistory.setUserId(user.getId());
        importHistoryService.importData(importHistory);
        return new ResultMap().success();
    }
}
