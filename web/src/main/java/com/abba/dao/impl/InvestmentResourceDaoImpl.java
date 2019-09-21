package com.abba.dao.impl;

import com.abba.dao.InvestmentResourceDao;
import com.abba.dao.base.AbstractHibernateDao;
import com.abba.model.po.InvestmentResource;
import org.springframework.stereotype.Repository;

/**
 * @author dengbojing
 */
@Repository
public class InvestmentResourceDaoImpl extends AbstractHibernateDao<InvestmentResource> implements InvestmentResourceDao<InvestmentResource> {
}
