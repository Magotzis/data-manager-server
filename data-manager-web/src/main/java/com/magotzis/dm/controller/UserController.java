package com.magotzis.dm.controller;

import com.magotzis.dm.component.pageHelper.PageQuery;
import com.magotzis.dm.model.Role;
import com.magotzis.dm.model.User;
import com.magotzis.dm.service.RoleService;
import com.magotzis.dm.service.UserService;
import com.magotzis.dm.util.ResultMap;
import com.magotzis.dm.vo.UserRegisterVo;
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

    @GetMapping
    public ResultMap getUsersList() {
        return new ResultMap().success(userService.listUsers());
    }

    @PostMapping
    public ResultMap registerUser(UserRegisterVo registerVo) {
        Assert.notNull(registerVo, "registerVo can not be null");
        Assert.hasText(registerVo.getUsername(), "username can not be empty");
        Assert.hasText(registerVo.getPassword(), "password can not be empty");
        Assert.hasText(registerVo.getRpassword(), "rpassword can not be empty");
        Assert.hasText(registerVo.getFullname(), "fullname can not be empty");
        Assert.hasText(registerVo.getEmail(), "email can not be empty");
        userService.registerUser(registerVo);
        return new ResultMap().success();
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
