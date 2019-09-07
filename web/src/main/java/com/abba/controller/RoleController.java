package com.abba.controller;

import com.abba.entity.response.BaseResponse;
import com.abba.model.bo.RoleParam;
import com.abba.model.dto.RoleDTO;
import com.abba.model.vo.RoleVO;
import com.abba.service.IRoleService;
import com.abba.util.StringHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author dengbojing
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private final IRoleService<RoleDTO> roleService;

    public RoleController(IRoleService<RoleDTO> roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/all")
    public BaseResponse<RoleVO> getAll(){
        return null;
    }

    @PostMapping("/loginUser")
    public BaseResponse<List<RoleVO>> getByLoginUser(@RequestBody RoleParam param){
        List<RoleDTO> dtos = roleService.getByUserId(param.getUserId());
        List<RoleVO> vos = dtos.stream().map(roleDTO -> new RoleVO(roleDTO)).collect(Collectors.toList());
        return BaseResponse.<List<RoleVO>>builder().build().adaptive(CollectionUtils::isNotEmpty, vos,"该用户没有对应角色","success");
    }
}
