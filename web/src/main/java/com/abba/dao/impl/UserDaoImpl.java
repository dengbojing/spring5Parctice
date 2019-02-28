package com.abba.dao.impl;

import com.abba.dao.AbstractHibernateDao;
import com.abba.dao.IUserDao;
import com.abba.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author dengbojing
 */
@Slf4j
@Repository
public class UserDaoImpl extends AbstractHibernateDao<User> implements IUserDao<User> {

    @Override
    public void update(User user) {
        this.updateByPrimaryKey(user);
    }

    @Override
    public void updateExceptiNull(User user) {
        this.mergeByPrimaryKey(user);
    }
}
