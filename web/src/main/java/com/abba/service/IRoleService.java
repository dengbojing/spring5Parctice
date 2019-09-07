package com.abba.service;

import com.abba.model.bo.RoleParam;

import java.util.List;
import java.util.Optional;

/**
 * @author dengbojing
 */
public interface IRoleService<T> {

    /**
     * 根据登陆用户id查询用户角色
     * @param userId 用户id
     */
    List<T> getByUserId(String userId);
}
