package com.abba.dao.impl;

import com.abba.dao.ICityFeatureDao;
import com.abba.dao.base.AbstractHibernateDao;
import com.abba.model.po.CityFeature;
import org.springframework.stereotype.Repository;

/**
 * @author dengbojing
 */
@Repository
public class CityFeatureDaoImpl extends AbstractHibernateDao<CityFeature> implements ICityFeatureDao<CityFeature> {
}
