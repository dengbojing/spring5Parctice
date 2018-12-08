package com.abba.controller;

import com.abba.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    UserService service;

    @RequestMapping("user")
    public String getUserName(){
        return service.getName();
    }
}
