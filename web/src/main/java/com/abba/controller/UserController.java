package com.abba.controller;

import com.abba.entity.BaseResponse;
import com.abba.entity.PageResponse;
import com.abba.model.User;
import com.abba.service.impl.UserServiceImpl;
import com.abba.util.StringHelper;
import com.abba.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.util.collections.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * @author dengbojing
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(final UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    /**
     * HATEOAS 练习测试
     * @return hateoas 链接
     */
    @RequestMapping("/overview")
    public HttpEntity<EntityModel<UserVO>> overview() {
        EntityModel<UserVO> model = new EntityModel<>(new UserVO(new User()),
                linkTo(methodOn(UserController.class).overview()).withSelfRel(),
                linkTo(methodOn(UserController.class).getAll()).withRel("all"),
                linkTo(methodOn(UserController.class).getUser("dengbojing")).withRel("queryByName"),
                linkTo(methodOn(UserController.class).createUser(new User())).withRel("add"));
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    /**
     * HATEOAS 练习测试
     * @return hateoas 链接
     */
    @RequestMapping("/concepts")
    public EntityModel<UserVO> concepts(){
        Class<UserController> controllerClass = UserController.class;
        Link findOneLink = linkTo(methodOn(controllerClass).concepts()).withSelfRel();
        return new EntityModel<>(new UserVO(new User()),
                findOneLink.andAffordance(afford(methodOn(controllerClass).getUser("dengbojing"))));

    }
    /**
     * 查询全部用户
     * @return 用户集合
     */
    @GetMapping("all")
    public PageResponse<UserVO> getAll(){
        List<User> list = userServiceImpl.queryAll();
        return PageResponse.<UserVO>builder().build().adaptive(CollectionHelper::isNotEmpty,
                list.stream().map(user -> new UserVO(user)).collect(Collectors.toList()));
    }

    /**
     * 根据用户姓名查询用户
     * @param name 姓名
     * @return 用户信息
     */
    @GetMapping("{name}")
    public BaseResponse<UserVO> getUser(@PathVariable String name) {
        User user = userServiceImpl.queryByName(name).orElse(User.builder().build());
        return BaseResponse.<UserVO>builder().build().
                adaptive(voTemp -> StringHelper.isNotEmpty(voTemp.getPid()), new UserVO(user),"没有该用户","success");
    }

    @PostMapping("/create")
    public BaseResponse<UserVO> createUser(@RequestBody User user) {
        log.info(user.toString());
        userServiceImpl.createUser(user);
        return BaseResponse.<UserVO>builder().build()
                .adaptive(voTemp -> StringHelper.isNotEmpty(voTemp.getPid()), new UserVO(user));
    }

    @PostMapping("/update")
    public BaseResponse<UserVO> updateUser(@RequestBody User user){
        Optional<User> optional = userServiceImpl.updateUser(user);
        return BaseResponse.<UserVO>builder().build().success("更新成功",new UserVO(optional.orElse(new User())));
    }

    @PostMapping(value = "/merge",consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<UserVO> mergeUser(@RequestBody User user){
        Optional<User> optional = userServiceImpl.updateExceptNull(user);
        return BaseResponse.<UserVO>builder().build().success("更新成功",new UserVO(optional.orElse(new User())));
    }
}
