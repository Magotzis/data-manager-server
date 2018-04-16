package com.magotzis.dm.api.service;

import com.magotzis.dm.api.dto.DataSourceDto;

import java.io.File;
import java.util.List;

/**
 * @author dengyq on 14:52 2018/2/7
 */
public interface DataManagementApiService {

    boolean importData(File file);

    void importData(String sql);

    String exportData(String sql);

    List<DataSourceDto> getDataSourceList();
}
