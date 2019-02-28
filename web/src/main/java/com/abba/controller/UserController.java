package com.abba.controller;

import com.abba.entity.BaseResponse;
import com.abba.model.User;
import com.abba.service.UserService;
import com.abba.util.StringHelper;
import com.abba.vo.UserVO;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public BaseResponse<UserVO> getUser(@PathVariable String name) {
        User user = userService.queryByName(name).orElse(User.builder().build());
        return BaseResponse.<UserVO>builder().build().
                adaptive(voTemp -> StringHelper.isNotEmpty(voTemp.getPid()), new UserVO(user),"没有该用户","success");
    }

    @PostMapping("/create")
    public BaseResponse<UserVO> createUser(@RequestBody User user) {
        log.info(user.toString());
        userService.createUser(user);
        return BaseResponse.<UserVO>builder().build()
                .adaptive(voTemp -> StringHelper.isNotEmpty(voTemp.getPid()), new UserVO(user));
    }

    @PostMapping("/update")
    public BaseResponse<UserVO> updateUser(@RequestBody User user){
        Optional<User> optional = userService.updateUser(user);
        return BaseResponse.<UserVO>builder().build().success("更新成功",new UserVO(optional.orElse(new User())));
    }

    @PostMapping(value = "/merge",consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<UserVO> mergeUser(@RequestBody User user){
        Optional<User> optional = userService.updateExceptiNull(user);
        return BaseResponse.<UserVO>builder().build().success("更新成功",new UserVO(optional.orElse(new User())));
    }
}
