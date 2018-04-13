package com.magotzis.dm.service;

import java.io.File;

public interface DataManagementService {
    boolean importData(File file);

    void importData(String sql);

    String exportData(String sql);
}
