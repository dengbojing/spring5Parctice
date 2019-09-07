package com.abba.controller;

import com.abba.entity.response.BaseResponse;
import com.abba.model.bo.PermissionParam;
import com.abba.model.dto.PermissionDTO;
import com.abba.service.IPermissionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengbojing
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    private final IPermissionService<PermissionDTO> permissionService;

    public PermissionController(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("/loginUser")
    public BaseResponse<PermissionDTO> getByLoginUser(@RequestBody PermissionParam param){
        return null;
    }


}
