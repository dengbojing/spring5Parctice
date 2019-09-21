package com.abba.service.impl;

import com.abba.dao.IPermissionDao;
import com.abba.model.bo.PermissionParam;
import com.abba.model.dto.PermissionDTO;
import com.abba.model.po.Permission;
import com.abba.service.IPermissionService;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author dengbojing
 */
@Service
public class PermissionServiceImpl implements IPermissionService<PermissionDTO> {

    private final IPermissionDao<Permission> permissionDao;

    public PermissionServiceImpl(IPermissionDao<Permission> permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public List<PermissionDTO> getByUserId(String userId) {
        StringBuilder sqlBuilder = new StringBuilder("select * from t_permission where id in (");
        sqlBuilder.append("select c_permission_id from t_role_permission where c_role_id in (");
        sqlBuilder.append("select c_role_id from t_user_role where c_user_id = ?0))");
        Map<Integer, Object> paramMap = new HashMap<>(2);
        paramMap.put(0,userId);
        List<Permission> permissions = permissionDao.sqlQueryList(sqlBuilder.toString(),paramMap);
        return permissions.stream().map(PermissionDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Optional<PermissionDTO> add(PermissionParam param) {
        Permission permission = new Permission();
        param.copyTo(permission);
        permissionDao.create(permission);
        return Optional.of(new PermissionDTO(permission));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Optional<PermissionDTO> update(PermissionParam param) {
        checkNotNull(param.getId());
        Permission permission = new Permission();
        param.copyTo(permission);
        permissionDao.mergeByPrimaryKey(permission);
        return Optional.of(new PermissionDTO(permission));
    }
}
