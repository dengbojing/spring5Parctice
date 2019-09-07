package com.abba.service.impl;

import com.abba.dao.IRoleDao;
import com.abba.model.bo.RoleParam;
import com.abba.model.dto.RoleDTO;
import com.abba.model.po.Role;
import com.abba.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.abba.entity.request.RequestHolder.userId;

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
    @Transactional(readOnly = true,rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<RoleDTO> getByUserId(String userId) {
        String sql = "select * from t_role where id in (select c_role_id from t_user_role where c_user_id = ?0)";
        Map<Integer,Object> paramMap = new HashMap<>(2);
        paramMap.put(0,userId);
        List<Role> roles = roleDao.sqlQueryList(sql,paramMap);
        return roles.stream().map(role -> new RoleDTO(role)).collect(Collectors.toList());
    }
}
