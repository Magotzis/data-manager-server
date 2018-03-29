package com.magotzis.dm.controller;

import com.magotzis.dm.component.pageHelper.PageQuery;
import com.magotzis.dm.model.Resource;
import com.magotzis.dm.service.ResourceService;
import com.magotzis.dm.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dengyq on 16:37 2018/3/28
 */
@RestController
@RequestMapping("/resources")
public class ResourceController {

    private ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    public ResultMap getResourcesPage(HttpServletRequest request) {
        PageQuery pageQuery = new PageQuery(request);
        return new ResultMap().page(resourceService.getResourcesPage(pageQuery));
    }

    @PostMapping
    public ResultMap addResource(Resource resource) {
        Assert.notNull(resource, "resource can't be null");
        Assert.hasText(resource.getDescription(), "description can't be null");
        Assert.hasText(resource.getMethod(), "method can't be null");
        resourceService.addResource(resource);
        return new ResultMap().success();
    }

    @PutMapping("/{id}")
    public ResultMap updateResource(@PathVariable("id") int id, String method, String description) {
        Assert.hasText(method, "method can't be null");
        Assert.hasText(description, "description can't be null");
        resourceService.updateResource(id, method, description);
        return new ResultMap().success();
    }

    @DeleteMapping("/{id}")
    public ResultMap deleteResource(@PathVariable("id") int id) {
        resourceService.deleteResource(id);
        return new ResultMap().success();
    }
}
