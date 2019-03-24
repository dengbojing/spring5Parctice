package com.abba.dao;

import java.util.List;
import java.util.Map;

/**
 * @author dengbojing
 */

public interface IUserDao<T> {

    T hqlQuery(String hql, Map<Integer, Object> params);

    void create(T t);

    void update(T t);

    void updateExceptNull(T t);

    List<T> hqlQueryList(String hql, Map<Integer,Object> params);

    T sqlQuery(String sql, Map<Integer,Object> params);

    List<T> sqlQueryList(String sql, Map<Integer,Object> params);

    T findEntity(String pid) ;

    void deleteByPrimaryKey(String pid);

    void updateByPrimaryKey(T t);

    void mergeByPrimaryKey(T t);

}
