package com.abba.dao;

import java.util.Map;

/**
 * @author dengbojing
 */

public interface IUserDao<T> {

    T hqlQuery(String hql, Map<Integer, Object> params);

    void create(T user);
}