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
        Query<User> query = session.createQuery("from User where userName = ?1",User.class);
        query.setCacheable(true).setParameter(1, name);
        return query.getSingleResult();
    }
}
