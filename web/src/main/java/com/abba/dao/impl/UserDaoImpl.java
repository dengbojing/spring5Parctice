package com.abba.dao.impl;

import com.abba.dao.IUserDao;
import com.abba.dao.base.AbstractHibernateDao;
import com.abba.model.po.User;
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
    public void updateExceptNull(User user) {
        this.mergeByPrimaryKey(user);
    }

}
