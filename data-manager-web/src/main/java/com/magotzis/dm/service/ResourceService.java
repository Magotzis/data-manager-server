package com.magotzis.dm.service;

import com.magotzis.dm.component.pageHelper.Page;
import com.magotzis.dm.component.pageHelper.PageQuery;
import com.magotzis.dm.model.Resource;

import java.util.List;

/**
 * @author dengyq on 11:36 2018/1/18
 */
public interface ResourceService {

    List<Resource> listResourceWithRoles();

    Page<Resource> getResourcesPage(PageQuery pageQuery);

    void addResource(Resource resource);

    void updateResource(int id, String method, String description);

    void deleteResource(int id);
}
