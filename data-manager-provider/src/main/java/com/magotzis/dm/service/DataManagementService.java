package com.magotzis.dm.service;

import com.magotzis.dm.api.dto.DataSourceDto;

import java.io.File;
import java.util.List;

public interface DataManagementService {
    boolean importData(File file);

    void importData(String sql);

    String exportData(String sql);

    List<DataSourceDto> getDataSourceList();
}
