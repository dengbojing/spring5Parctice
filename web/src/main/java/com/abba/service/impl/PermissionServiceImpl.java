package com.abba.service.impl;

import com.abba.dao.IPermissionDao;
import com.abba.model.dto.PermissionDTO;
import com.abba.model.po.Permission;
import com.abba.service.IPermissionService;
import org.springframework.stereotype.Service;

/**
 * @author dengbojing
 */
@Service
public class PermissionServiceImpl implements IPermissionService<PermissionDTO> {

    private final IPermissionDao<Permission> permissionDao;

    public PermissionServiceImpl(IPermissionDao<Permission> permissionDao) {
        this.permissionDao = permissionDao;
    }
}
