package com.magotzis.dm.service;

import java.io.File;

public interface DataManagementService {
    boolean importData(File file);

    boolean importData(String sql);
}
