package com.abba.service;

import com.abba.model.bo.PermissionParam;

import java.util.List;
import java.util.Optional;

/**
 * @author dengbojing
 */
public interface IPermissionService<T> {

    /**
     * 根据用户id获取用户权限
     * @param userId 用户id
     * @return 权限列表
     */
    List<T> getByUserId(String userId);

    /**
     * 添加权限
     * @param param 权限参数
     * @return 添加后的权限信息
     */
    Optional<T> add(PermissionParam param);

    /**
     * 更新权限
     * @param param 权限参数
     * @return 更新后的权限信息
     */
    Optional<T> update(PermissionParam param);
}
