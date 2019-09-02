package com.abba.dao.impl;

import com.abba.dao.ICompanyDao;
import com.abba.dao.base.AbstractHibernateDao;
import com.abba.model.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author dengbojing
 */
@Slf4j
@Repository
public class CompanyDaoImpl extends AbstractHibernateDao<Company> implements ICompanyDao<Company> {
}
