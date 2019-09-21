package com.abba.service;

import com.abba.model.bo.MenuParam;

import java.util.List;
import java.util.Optional;

/**
 * @author dengbojing
 */
public interface IMenuService<T> {

    /**
     * 根据用户id查询所有菜单
     * @param userId 用户id
     * @return 返回菜单列表
     */
    List<T> getUserById(String userId);

    /**
     * 添加菜单
     * @param param 菜单参数
     * @return 菜单
     */
    Optional<T> add(MenuParam param);

    /**
     * 查询所有菜单
     * @return 菜单列表
     */
    List<T> getAll();

    /**
     * 更新菜单
     * @param param
     * @return 更新后的对象
     */
    Optional<T> update(MenuParam param);
}
