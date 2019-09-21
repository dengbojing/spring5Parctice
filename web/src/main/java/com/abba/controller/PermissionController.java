package com.abba.controller;

import com.abba.entity.response.BaseResponse;
import com.abba.model.bo.PermissionParam;
import com.abba.model.dto.PermissionDTO;
import com.abba.model.vo.PermissionVO;
import com.abba.service.IPermissionService;
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
@RequestMapping("/permission")
public class PermissionController {

    private final IPermissionService<PermissionDTO> permissionService;

    public PermissionController(IPermissionService<PermissionDTO> permissionService) {
        this.permissionService = permissionService;
    }


    /**
     * 查看当前登录用去权限
     * @param param 参数占位符--用来获取当前登陆用户id
     * @return 统一返回格式 {@link BaseResponse}
     */
    @PostMapping("/loginUser")
    public BaseResponse<List<PermissionVO>> getByLoginUser(@RequestBody PermissionParam param){
        List<PermissionDTO> dtos = permissionService.getByUserId(param.getUserId());
        List<PermissionVO> vos = dtos.stream().map(PermissionVO::new).collect(Collectors.toList());
        return BaseResponse.<List<PermissionVO>>builder().build().adaptive(CollectionUtils::isNotEmpty, vos);
    }

    /**
     * 添加权限
     * @param param 权限参数
     * @return 统一返回格式 {@link BaseResponse}
     */
    @PostMapping("/add")
    public BaseResponse<PermissionVO> add(@RequestBody PermissionParam param){
        PermissionDTO dto = permissionService.add(param).orElseGet(PermissionDTO::new);
        return BaseResponse.<PermissionVO>builder().build()
                .adaptive(permissionVO -> StringHelper.isNotEmpty(permissionVO.getId()), new PermissionVO(dto));
    }

    /**
     * 更新权限信息
     * @param param
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<String> update(@RequestBody PermissionParam param){
        permissionService.update(param);
        return BaseResponse.<String>builder().build().success("");
    }


}
