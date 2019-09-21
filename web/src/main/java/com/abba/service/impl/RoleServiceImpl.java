package com.abba.service.impl;

import com.abba.dao.IRoleDao;
import com.abba.model.bo.RoleParam;
import com.abba.model.dto.RoleDTO;
import com.abba.model.po.Role;
import com.abba.service.IRoleService;
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
public class RoleServiceImpl implements IRoleService<RoleDTO> {

    private final IRoleDao<Role> roleDao;

    public RoleServiceImpl(IRoleDao<Role> roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<RoleDTO> getAll() {
        List<Role> roles = roleDao.findAll();
        return roles.stream().map(RoleDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<RoleDTO> getByUserId(String userId) {
        String sql = "select * from t_role where id in (select c_role_id from t_user_role where c_user_id = ?0)";
        Map<Integer,Object> paramMap = new HashMap<>(2);
        paramMap.put(0,userId);
        List<Role> roles = roleDao.sqlQueryList(sql,paramMap);
        return roles.stream().map(RoleDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Optional<RoleDTO> add(RoleParam param) {
        Role role = new Role();
        param.copyTo(role);
        roleDao.create(role);
        return Optional.of(new RoleDTO(role));
    }


    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Optional<RoleDTO> update(RoleParam param) {
        checkNotNull(param.getId(),"角色id不能为空");
        Role role = new Role();
        param.copyTo(role);
        roleDao.mergeByPrimaryKey(role);
        return Optional.of(new RoleDTO(role));
    }
}
