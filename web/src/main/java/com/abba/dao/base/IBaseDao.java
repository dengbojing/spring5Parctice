package com.abba.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author dengbojing
 * @param <T>
 */
public interface IBaseDao<T extends Serializable> {


    T hqlQuery(String hql, Map<Integer, Object> params);

    void create(T t);

    void insert(T t);

    List<T> hqlQueryList(String hql, Map<Integer,Object> params);

    T sqlQuery(String sql, Map<Integer,Object> params);

    List<T> sqlQueryList(String sql, Map<Integer,Object> params);

    T findByPrimaryKey(String pid) ;

    void deleteByPrimaryKey(String pid);

    void updateByPrimaryKey(T t);

    void mergeByPrimaryKey(T t);
}
