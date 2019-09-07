package com.abba.service;

import com.abba.model.bo.LoginParam;
import com.abba.model.bo.UserParam;
import com.abba.model.dto.UserDTO;
import com.abba.model.po.User;

import java.util.List;
import java.util.Optional;

/**
 * @author dengbojing
 * @param <T>
 */
public interface IUserService<T> {


    /**
     * 更具用户姓名查询用户
     * @param name 用户姓名
     * @return 用户信息 {@link com.abba.model.dto.UserDTO}
     */
    Optional<T> getByName(String name);

    /**
     *  分页查询
     * @return
     */
    List<T> page();

    /**
     * 创建用户
     * @param user
     */
    Optional<T> createUser(UserParam user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    Optional<T> updateUser(UserParam user);

    /**
     * 更新用户,排除空字段
     * @param user
     * @return
     */
    Optional<T> updateExceptNull(UserParam user);

    /**
     * 查询所有用户
     * @return
     */
    List<T> queryAll();

    /**
     * 根据用户id查询用户
     * @param userId 用户id
     * @return
     */
    Optional<T> getById(String userId);

    /**
     * 根据用户id查询用户基本信息
     * @param userId
     * @return
     */
    Optional<T> getInfoById(String userId);
}
