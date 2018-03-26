package com.magotzis.dm.controller;

import com.magotzis.dm.component.pageHelper.PageQuery;
import com.magotzis.dm.model.Role;
import com.magotzis.dm.model.User;
import com.magotzis.dm.service.RoleService;
import com.magotzis.dm.service.UserService;
import com.magotzis.dm.util.ResultMap;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author dengyq on 16:33 2018/1/12
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @GetMapping()
    public ResultMap getUsersPage(HttpServletRequest request) {
        PageQuery pageQuery = new PageQuery(request);
        return new ResultMap().page(userService.getUserPage(pageQuery));
    }

    @DeleteMapping("/{username}")
    public ResultMap deleteUser(@PathVariable("username") String username) {
        Assert.isTrue(!StringUtils.isEmpty(username), "username could not be empty");
        userService.deleteUser(username);
        return new ResultMap().success();
    }

    @PutMapping("/{username}/roles")
    public ResultMap updateUserRoles(@PathVariable("username") String username, @RequestBody List<Role> roles) {
        Assert.isTrue(!StringUtils.isEmpty(username), "username could not be empty");
        userService.updateUserRoles(username,roles);
        return new ResultMap().success();
    }

    @GetMapping("/{username}/roles")
    public ResultMap getUserRoles(@PathVariable("username") String username) {
        Assert.isTrue(!StringUtils.isEmpty(username), "username could not be empty");
        User user = userService.getUserAndRolesByUsername(username);
        return new ResultMap().success(user.getRoles());
    }

    @GetMapping("/roles")
    public ResultMap getAllRoles() {
        return new ResultMap().success(roleService.getAll());
    }
}
