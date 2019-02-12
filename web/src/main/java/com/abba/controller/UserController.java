package com.abba.controller;

import com.abba.entity.BaseResponse;
import com.abba.exception.ResourceNotFoundException;
import com.abba.model.User;
import com.abba.service.UserService;
import com.abba.util.StringJudge;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author dengbojing
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{name}")
    public BaseResponse<User> getUser(@PathVariable String name){
        User user = userService.queryByName(name).orElse(new User());
        return BaseResponse.<User>builder().build().success("success", user);
    }

    @PostMapping("/create")
    public BaseResponse<User> createUser(@RequestBody  User user){
        log.info(user.toString());
        userService.createUser(user);
        return BaseResponse.<User>builder().build().adaptive(userTemp -> StringJudge.isNotEmpty(userTemp.getPid()), user);
    }
}
