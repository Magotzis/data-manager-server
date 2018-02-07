package com.magotzis.dm.controller;

import com.magotzis.dm.service.UserService;
import com.magotzis.dm.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dengyq on 16:33 2018/1/12
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsersList() {
        return userService.listUsers();
    }
}
