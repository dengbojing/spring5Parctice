package com.abba.dao.impl;

import com.abba.dao.AbstractHibernateDao;
import com.abba.dao.IUserDao;
import com.abba.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author dengbojing
 */
@Repository
public class UserDaoImpl extends AbstractHibernateDao<User> implements IUserDao<User> {

    UserDaoImpl(){
        this.clazz = User.class;
    }


}
