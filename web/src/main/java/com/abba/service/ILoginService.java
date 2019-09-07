package com.abba.service;

import com.abba.model.bo.LoginParam;

import java.util.Optional;

/**
 * @author dengbojing
 */
public interface ILoginService<T> {

    /**
     * 用户登录
     * @param loginParam 登录参数(用户名,密码)
     * @return 用户信息 {@link com.abba.model.dto.UserDTO}
     */
    Optional<String> login(LoginParam loginParam);
}
