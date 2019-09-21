package com.abba.service.impl;

import com.abba.dao.IUserDao;
import com.abba.entity.AbstractDTO;
import com.abba.entity.request.Pager;
import com.abba.model.bo.UserParam;
import com.abba.model.dto.UserDTO;
import com.abba.model.po.User;
import com.abba.service.IUserService;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @author dengbojing
 */

@Service
@Slf4j
public class UserServiceImpl implements IUserService<UserDTO> {

    private final IUserDao<User> userDao;

    @Autowired
    public UserServiceImpl(IUserDao<User> userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public Optional<UserDTO> getByName(String name) {
        String hql = "from User where userName = ?1";
        Map<Integer,Object> params = new HashMap<>(2);
        params.put(1, name);
        return Optional.of(new UserDTO(userDao.hqlQuery(hql,params)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Optional<UserDTO> createUser(UserParam param){
        User user = new User();
        param.copyTo(user);
        userDao.create(user);
        return Optional.of(new UserDTO(user));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Optional<UserDTO> updateUser(UserParam param) {
        User user = new User();
        param.copyTo(user);
        userDao.update(user);
        return Optional.of(new UserDTO(user));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Optional<UserDTO> updateExceptNull(UserParam param) {
        User user = new User();
        param.copyTo(user);
        userDao.updateExceptNull(user);
        return Optional.of(new UserDTO(user));
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public List<UserDTO> queryAll() {
        return userDao.hqlQueryList("from User where 1=1", null).stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public List<UserDTO> page(){
        return userDao.page(new Pager<>()).getData().stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public Optional<UserDTO> getById(String userId) {
        User user = userDao.findByPrimaryKey(userId);
        UserDTO dto;
        dto = JSON.parseObject(JSON.toJSONString(user), UserDTO.class);
        return Optional.of(dto);
    }


    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public Optional<UserDTO> getInfoById(String userId) {
        String hql = "from User where id = ?0";
        final Map<Integer, Object> paramMap = new HashMap<>(2);
        paramMap.put(0,userId);
        User user = userDao.hqlQuery(hql,paramMap);
        return Optional.of(new UserDTO(user));
    }
}
