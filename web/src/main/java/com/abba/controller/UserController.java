package com.abba.controller;

import com.abba.entity.response.BaseResponse;
import com.abba.entity.response.PageResponse;
import com.abba.model.bo.UserParam;
import com.abba.model.dto.UserDTO;
import com.abba.service.IUserService;
import com.abba.util.StringHelper;
import com.abba.model.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.util.collections.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
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

    private final IUserService<UserDTO> userService;

    @Autowired
    public UserController(final IUserService<UserDTO> userService) {
        this.userService = userService;
    }


    /**
     * HATEOAS 练习测试
     * @return hateoas 链接
     */
    @RequestMapping("/overview")
    public HttpEntity<EntityModel<UserVO>> overview() {
        EntityModel<UserVO> model = new EntityModel<>(new UserVO(new UserDTO()),
                linkTo(methodOn(UserController.class).overview()).withSelfRel(),
                linkTo(methodOn(UserController.class).getAll()).withRel("all"),
                linkTo(methodOn(UserController.class).getUser("dengbojing")).withRel("queryByName"),
                linkTo(methodOn(UserController.class).createUser(new UserParam())).withRel("add"));
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
        return new EntityModel<>(new UserVO(new UserDTO()),
                findOneLink.andAffordance(afford(methodOn(controllerClass).getUser("dengbojing"))));

    }
    /**
     * 查询全部用户
     * @return 用户集合
     */
    @GetMapping("all")
    public PageResponse<UserVO> getAll(){
        List<UserDTO> list = userService.page();
        return PageResponse.<UserVO>builder().build().adaptive(CollectionHelper::isNotEmpty,
                list.stream().map(dto -> new UserVO(dto)).collect(Collectors.toList()));
    }

    /**
     * 根据用户姓名查询用户
     * @param name 姓名
     * @return 用户信息
     */
    @GetMapping("{name}")
    public BaseResponse<UserVO> getUser(@PathVariable String name) {
        UserDTO dto = userService.getByName(name).orElse(new UserDTO());
        return BaseResponse.<UserVO>builder().build().
                adaptive(voTemp -> StringHelper.isNotEmpty(voTemp.getId()), new UserVO(dto),"没有该用户","success");
    }


    /**
     * 根据用户登陆id查询用户详细信息(包含用户角色等)
     * @param param
     * @return
     */
    @PostMapping("/loginUser/detail")
    public BaseResponse<UserVO> getLoginUserDetail(@RequestBody UserParam param){
        UserDTO dto = userService.getById(param.getUserId()).orElse(new UserDTO());
        return BaseResponse.<UserVO>builder().build().adaptive(voTemp -> StringHelper.isNotEmpty(voTemp.getId()),new UserVO(dto),"未查到该用户","success");
    }

    /**
     * 根据用户登陆id查询用户详细信息
     * @param param
     * @return
     */
    @PostMapping("/loginUser/info")
    public BaseResponse<UserVO> getLoginUserInfo(@RequestBody UserParam param){
        UserDTO dto = userService.getInfoById(param.getUserId()).orElse(new UserDTO());
        return BaseResponse.<UserVO>builder().build().adaptive(voTemp -> StringHelper.isNotEmpty(voTemp.getId()),new UserVO(dto),"未查到该用户","success");
    }




    /**
     * 创建用户
     * @param param
     * @return
     */
    @PostMapping("/create")
    public BaseResponse<UserVO> createUser(@RequestBody UserParam param) {
        log.info(param.toString());
        return BaseResponse.<UserVO>builder().build()
                .adaptive(voTemp -> StringHelper.isNotEmpty(voTemp.getId()), new UserVO(userService.createUser(param).orElseGet(UserDTO::new)));
    }

    /**
     * 更新用户
     * @param param 用户信息
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<UserVO> updateUser(@RequestBody UserParam param){
        Optional<UserDTO> optional = userService.updateExceptNull(param);
        return BaseResponse.<UserVO>builder().build().success("更新成功",new UserVO(optional.get()));
    }



}
