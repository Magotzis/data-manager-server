package com.magotzis.dm.service.impl;

import com.magotzis.dm.dao.LoginLogDao;
import com.magotzis.dm.model.LoginLog;
import com.magotzis.dm.service.LoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dengyq on 9:43 2018/2/1
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Resource
    private LoginLogDao loginLogDao;

    @Override
    public void insertLog(LoginLog loginLog) {
        loginLogDao.insertLog(loginLog);
    }
}
