package com.magotzis.dm.service.impl;

import com.magotzis.dm.dao.ResourceDao;
import com.magotzis.dm.model.Resource;
import com.magotzis.dm.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dengyq on 11:38 2018/1/18
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    private ResourceDao resourceDao;

    @Autowired
    public ResourceServiceImpl(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    @Override
    public List<Resource> listResourceWithRoles() {
        return resourceDao.listResourceWithRoles();
    }
}
