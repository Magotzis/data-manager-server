package com.magotzis.dm.auth;

import com.magotzis.dm.model.LoginLog;
import com.magotzis.dm.service.LoginLogService;
import com.magotzis.dm.model.User;
import com.magotzis.dm.util.IpUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author dengyq on 17:13 2018/1/30
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Resource
    private LoginLogService loginLogService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        User user = (User) authentication.getPrincipal();
        // 添加登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getId());
        loginLog.setIp(IpUtil.getIpAddress(request));
        loginLog.setCreateDt(new Date());
        loginLogService.insertLog(loginLog);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
