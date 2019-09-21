package com.abba.dao.impl;

import com.abba.dao.IndustrialParkDao;
import com.abba.dao.base.AbstractHibernateDao;
import com.abba.model.po.IndustrialPark;
import org.springframework.stereotype.Repository;

/**
 * @author dengbojing
 */
@Repository
public class IndustrialParkDaoImpl extends AbstractHibernateDao<IndustrialPark> implements IndustrialParkDao<IndustrialPark> {
}
