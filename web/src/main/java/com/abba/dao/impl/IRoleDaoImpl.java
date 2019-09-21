package com.abba.dao.impl;

import com.abba.dao.IRoleDao;
import com.abba.dao.base.AbstractHibernateDao;
import com.abba.model.po.Role;
import org.springframework.stereotype.Repository;

/**
 * @author dengbojing
 */
@Repository
public class IRoleDaoImpl extends AbstractHibernateDao<Role> implements IRoleDao<Role> {
}
