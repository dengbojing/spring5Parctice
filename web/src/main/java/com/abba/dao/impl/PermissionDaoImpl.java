package com.abba.dao.impl;

import com.abba.dao.IPermissionDao;
import com.abba.dao.base.AbstractHibernateDao;
import com.abba.model.po.Permission;
import org.springframework.stereotype.Repository;

/**
 * @author dengbojing
 */
@Repository
public class PermissionDaoImpl extends AbstractHibernateDao<Permission> implements IPermissionDao<Permission> {
}
