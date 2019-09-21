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

import java.util.List;
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
    public BaseResponse<List<RoleVO>> getAll(){
        List<RoleDTO> dtos = roleService.getAll();
        List<RoleVO> vos = dtos.stream().map(RoleVO::new).collect(Collectors.toList());
        return BaseResponse.<List<RoleVO>>builder().build().success(vos);
    }

    /**
     * 查看登录用户的所有角色
     * @param param 参数占位符--主要用来获取当前登录用户id
     * @return 统一返回格式 {@link BaseResponse}
     */
    @PostMapping("/loginUser")
    public BaseResponse<List<RoleVO>> getByLoginUser(@RequestBody RoleParam param){
        List<RoleDTO> dtos = roleService.getByUserId(param.getUserId());
        List<RoleVO> vos = dtos.stream().map(RoleVO::new).collect(Collectors.toList());
        return BaseResponse.<List<RoleVO>>builder().build().adaptive(CollectionUtils::isNotEmpty, vos,"该用户没有对应角色","success");
    }

    /**
     * 添加角色
     * @param param 角色参数
     * @return 统一返回格式 {@link BaseResponse}
     */
    @PostMapping("/add")
    public BaseResponse<RoleVO> addRole(@RequestBody RoleParam param){
        RoleDTO dto = roleService.add(param).orElseGet(RoleDTO::new);
        return BaseResponse.<RoleVO>builder().build()
                .adaptive(roleVO -> StringHelper.isNotEmpty(roleVO.getId()), new RoleVO(dto),"插入失败","success");
    }

    /**
     * 更新橘色信息
     * @param param
     * @return 统一返回格式 {@link BaseResponse}
     */
    @PostMapping("/update")
    public BaseResponse<String> updateRole(@RequestBody RoleParam param){
        roleService.update(param);
        return BaseResponse.<String>builder().build().success("");
    }
}
