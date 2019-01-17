package com.abba.service;

import com.abba.model.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dengbojing
 */

@Service
@Slf4j
public class UserService{

    @Autowired
    BaseDAO<User> dao;

    @Transactional
    public User queryByName(String name) {
        Session session = dao.getSession();
        User user = null;
        try {
            Query query = session.createQuery("from User where userName = ?1");
            query.setParameter(1, name);
            List<User> list = query.list();
            if (list.size() > 0) {
                user = list.get(0);
            }
        } catch (HibernateException e) {
            log.error("加载出错！",e);
        }
        return user;
    }
}
