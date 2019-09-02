package com.abba.dao.impl;

import com.abba.dao.IUserDao;
import com.abba.dao.base.AbstractHibernateDao;
import com.abba.entity.vo.Pager;
import com.abba.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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
