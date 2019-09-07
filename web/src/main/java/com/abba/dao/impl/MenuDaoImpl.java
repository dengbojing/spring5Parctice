package com.abba.dao.impl;

import com.abba.dao.IMenuDao;
import com.abba.dao.base.AbstractHibernateDao;
import com.abba.model.po.Menu;
import org.springframework.stereotype.Repository;

/**
 * @author dengbojing
 */
@Repository
public class MenuDaoImpl extends AbstractHibernateDao<Menu> implements IMenuDao<Menu> {
}
