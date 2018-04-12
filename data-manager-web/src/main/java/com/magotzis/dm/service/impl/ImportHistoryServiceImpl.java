package com.magotzis.dm.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.magotzis.dm.api.exception.data.SqlExecuteFailException;
import com.magotzis.dm.api.service.DataManagementApiService;
import com.magotzis.dm.dao.ImportHistoryDao;
import com.magotzis.dm.enums.ImportHistoryState;
import com.magotzis.dm.model.ImportHistory;
import com.magotzis.dm.service.ImportHistoryService;
import com.magotzis.dm.vo.ImportHistoryVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author dengyq on 10:36 2018/4/10
 */
@Service
public class ImportHistoryServiceImpl implements ImportHistoryService {

    @Reference
    private DataManagementApiService dataManagementApiService;

    @Resource
    private ImportHistoryDao importHistoryDao;

    @Override
    public List<ImportHistoryVo> getList() {
        return importHistoryDao.getList();
    }

    @Override
    public ImportHistory getById(int id) {
        return importHistoryDao.findById(id);
    }

    @Override
    public void importData(ImportHistory importHistory) {
        try {
            dataManagementApiService.importData(importHistory.getContent());
            importHistory.setState(ImportHistoryState.SUCCESS.getState());
            importHistory.setErrorMsg(null);
        } catch (SqlExecuteFailException e) {
            importHistory.setState(ImportHistoryState.FAIL.getState());
            importHistory.setErrorMsg(e.getMessage());
        }
        importHistory.setUpdateTime(new Date());
        importHistoryDao.insert(importHistory);
    }
}
