package com.magotzis.dm.service;

import com.magotzis.dm.model.ImportHistory;
import com.magotzis.dm.vo.ImportHistoryVo;

import java.util.List;

/**
 * @author dengyq on 10:36 2018/4/10
 */
public interface ImportHistoryService {
    List<ImportHistoryVo> getList();

    ImportHistory getById(int id);

    void importData(ImportHistory importHistory);
}
