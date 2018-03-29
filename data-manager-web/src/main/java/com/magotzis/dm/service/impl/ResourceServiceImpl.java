package com.magotzis.dm.service.impl;

import com.magotzis.dm.component.pageHelper.Page;
import com.magotzis.dm.component.pageHelper.PageCallBack;
import com.magotzis.dm.component.pageHelper.PageCallBackUtil;
import com.magotzis.dm.component.pageHelper.PageQuery;
import com.magotzis.dm.dao.ResourceDao;
import com.magotzis.dm.exception.auth.ResourceNotExistException;
import com.magotzis.dm.model.Resource;
import com.magotzis.dm.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public Page<Resource> getResourcesPage(PageQuery pageQuery) {
        Page<Resource> page = PageCallBackUtil.selectPage(pageQuery, () -> resourceDao.listResources(pageQuery.getSearch()));
        page.setRecordsTotal(resourceDao.count());
        return page;
    }

    @Override
    public void addResource(Resource resource) {
        resource.setCreateTime(new Date());
        resourceDao.add(resource);
    }

    @Override
    public void updateResource(int id, String method, String description) {
        Resource resource = resourceDao.findById(id);
        if (resource == null) {
            throw new ResourceNotExistException();
        }
        resource.setMethod(method);
        resource.setDescription(description);
        resource.setUpdateTime(new Date());
        resourceDao.update(resource);
    }

    @Override
    public void deleteResource(int id) {
        Resource resource = resourceDao.findById(id);
        if (resource == null) {
            throw new ResourceNotExistException();
        }
        resourceDao.delete(id);
    }
}
