package com.magotzis.dm.service.impl;

import com.magotzis.dm.dao.RoleDao;
import com.magotzis.dm.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dengyq on 11:41 2018/1/18
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;
}
