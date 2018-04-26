package com.magotzis.dm.service;

import com.magotzis.dm.api.dto.DataSourceDto;
import com.magotzis.dm.model.DataHistory;
import com.magotzis.dm.vo.DataHistoryVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author dengyq on 10:36 2018/4/10
 */
public interface DataHistoryService {
    List<DataHistoryVo> getList(int type);

    DataHistory getById(int id, int type);

    void importData(DataHistory dataHistory);

    void exportData(DataHistory dataHistory, HttpServletResponse response);

    List<DataSourceDto> getDataSourceList();
}
