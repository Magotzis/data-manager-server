package com.magotzis.dm.auth;

import com.magotzis.dm.model.Resource;
import com.magotzis.dm.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dengyq on 17:28 2018/1/17
 */
@Service
public class CustomInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private ResourceService resourceService;

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        if (resourceMap == null) {
            loadResourceDefine();
        }
        Iterator<String> iterator = resourceMap.keySet().iterator();
        while (iterator.hasNext()) {
            String res = iterator.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(res);
            if (requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return resourceMap.get(res);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<>();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @PostConstruct
    private void loadResourceDefine() {
        resourceMap = new HashMap<>();
        List<Resource> resources = resourceService.listResourceWithRoles();
        if (!CollectionUtils.isEmpty(resources)) {
            resources.forEach(resource -> {
                Collection<ConfigAttribute> collect = resource.getRoles().stream()
                        .map(role -> new SecurityConfig(role.getRole())).collect(Collectors.toList());
                resourceMap.put(resource.getMethod(), collect);
            });
        }
    }
}
