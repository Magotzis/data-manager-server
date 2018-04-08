package com.magotzis.dm.api.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.magotzis.dm.api.service.DataManagementApiService;
import com.magotzis.dm.service.DataManagementService;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author dengyq on 14:55 2018/2/7
 */
@Service
public class DataManagementApiServiceImpl implements DataManagementApiService{
    @Resource
    private DataManagementService dataManagementService;

    @Override
    public boolean importData(File file) {
        Assert.notNull(file, "file can not be bull");
        return dataManagementService.importData(file);
    }

    @Override
    public boolean importData(String sql) {
        Assert.hasText(sql, "sql can not be null");
        return dataManagementService.importData(sql);
    }
}
