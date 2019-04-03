package com.abba.dao.impl;

import com.abba.dao.base.AbstractHibernateDao;
import com.abba.dao.IUserDao;
import com.abba.entity.vo.Pager;
import com.abba.model.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaQuery;
import org.springframework.stereotype.Repository;

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

    @Override
    public List<User> page(Pager pager) {
        /*CriteriaQuery criteria = this.getCurrentSession().createNativeQuery(User.class);
        criteria.setFirstResult(0);
        criteria.setMaxResults(pageSize);
        List<Foo> firstPage = criteria.list();*/
        return null;
    }
}
