package com.abba.controller;

import com.abba.entity.BaseResponse;
import com.abba.exception.ResourceNotFoundException;
import com.abba.model.User;
import com.abba.service.UserService;
import com.abba.util.StringJudge;
import com.abba.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
                adaptive(voTemp -> StringJudge.isNotEmpty(voTemp.getPid()), new UserVO(user),"没有该用户","success");
    }

    @PostMapping("/create")
    public BaseResponse<UserVO> createUser(@RequestBody User user) {
        log.info(user.toString());
        userService.createUser(user);
        return BaseResponse.<UserVO>builder().build()
                .adaptive(voTemp -> StringJudge.isNotEmpty(voTemp.getPid()), new UserVO(user));
    }

    @PostMapping("/update")
    public BaseResponse<UserVO> updateUser(@RequestBody User user){
        Optional<User> optional = userService.updateUser(user);
        return BaseResponse.<UserVO>builder().build().success("更新成功",new UserVO(optional.orElse(new User())));
    }
}
