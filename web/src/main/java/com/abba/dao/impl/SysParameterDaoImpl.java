package com.abba.dao.impl;

import com.abba.dao.ISysParameterDao;
import com.abba.dao.base.AbstractHibernateDao;
import com.abba.model.SysParameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author dengbojing
 */
@Slf4j
@Repository
public class SysParameterDaoImpl extends AbstractHibernateDao<SysParameter> implements ISysParameterDao<SysParameter> {
}
