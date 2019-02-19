package com.abba.service;

import com.abba.dao.IUserDao;
import com.abba.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * @author dengbojing
 */

@Service
@Slf4j
public class UserService{

    private final IUserDao<User> userDao;

    @Autowired
    public UserService(IUserDao<User> userDao) {
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
        //TODO
        return Optional.of(user);
    }
}
