package com.magotzis.dm.api.service;

import java.io.File;

/**
 * @author dengyq on 14:52 2018/2/7
 */
public interface DataManagementApiService {

    boolean importData(File file);

    void importData(String sql);

    String exportData(String sql);
}
