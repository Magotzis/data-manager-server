package com.magotzis.dm.auth;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;

/**
 * @author dengyq on 15:18 2018/1/24
 */
@Component
public class CustomFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter{

    @Resource
    private CustomInvocationSecurityMetadataSource securityMetadataSource;

    @Resource
    private CustomAccessDecisionManager accessDecisionManager;

    @Resource
    private AuthenticationManager authenticationManager;

    @PostConstruct
    public void init(){
        super.setAuthenticationManager(authenticationManager);
        super.setAccessDecisionManager(accessDecisionManager);
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation filterInvocation = new FilterInvocation(servletRequest,servletResponse,filterChain);
        invoke(filterInvocation);
    }

    private void invoke(FilterInvocation filterInvocation) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
        try {
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(),filterInvocation.getResponse());
        }finally {
            super.afterInvocation(token,null);
        }
    }

    @Override
    public void destroy() {

    }
}
