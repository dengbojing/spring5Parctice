package com.abba.service.impl;

import com.abba.dao.IUserDao;
import com.abba.entity.BaseResponse;
import com.abba.model.User;
import com.abba.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * @author dengbojing
 */

@Service
@Slf4j
public class UserServiceImpl {

    private final IUserDao<User> userDao;

    @Autowired
    public UserServiceImpl(IUserDao<User> userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public Optional<User> queryByName(String name) {
        String hql = "from User where userName = ?1";
        Map<Integer,Object> params = new HashMap<>(2);
        params.put(1, name);
        return Optional.ofNullable(userDao.hqlQuery(hql,params));
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Optional<User> createUser(User user){
        userDao.create(user);
        return Optional.ofNullable(user);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Optional<User> updateUser(User user) {
        userDao.update(user);
        return Optional.of(user);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Optional<User> updateExceptNull(User user) {
        userDao.updateExceptNull(user);
        return Optional.of(user);
    }

    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public List<User> queryAll() {
        return userDao.hqlQueryList("from User where 1=1", null);
    }
}
