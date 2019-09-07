package com.abba.service;

import java.util.List;
import java.util.Optional;

/**
 * @author dengbojing
 */
public interface IMenuService<T> {

    /**
     * 根据用户id查询所有菜单
     * @param userId
     * @return
     */
    List<T> getUserById(String userId);
}
