package com.magotzis.dm.dao;

import com.magotzis.dm.model.LoginLog;
import org.springframework.stereotype.Repository;

/**
 * @author dengyq on 9:44 2018/2/1
 */
@Repository
public interface LoginLogDao {

    void insertLog(LoginLog loginLog);
}
