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

    /**
     * 添加角色
     * @param param 角色参数
     * @return
     */
    Optional<T> add(RoleParam param);

    /**
     * 更新角色
     * @param param 角色参数
     */
    Optional<T>  update(RoleParam param);

    /**
     * 查看所有角色
     * @return 角色列表
     */
    List<T> getAll();
}
