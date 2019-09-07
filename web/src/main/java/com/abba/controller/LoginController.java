package com.abba.controller;

import com.abba.entity.response.BaseResponse;
import com.abba.model.bo.LoginParam;
import com.abba.model.dto.UserDTO;
import com.abba.service.ILoginService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengbojing
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private final ILoginService<UserDTO> loginService;

    public LoginController(ILoginService<UserDTO> userService) {
        this.loginService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<String> login(@RequestBody LoginParam loginParam){
        return BaseResponse.<String>builder().build().success("登录成功",loginService.login(loginParam).orElseGet(String::new));

    }
}
