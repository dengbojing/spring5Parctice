package com.abba.dao.base;

import com.abba.entity.request.Pager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author dengbojing
 * @param <T>
 */
public interface IBaseDao<T extends Serializable> {


    /**
     * hql查询
     * @param hql hql语句
     * @param params key: position, value: real-value
     * @return T --> the query result
     */
    T hqlQuery(String hql, Map<Integer, Object> params);

    /**
     * 创建对象
     * @param t 实体对象
     */
    void create(T t);

    /**
     * 创建对象
     * @param t 实体对象
     */
    void insert(T t);

    /**
     * hql查询
     * @param hql hql语句
     * @param params key: position, value: real-value
     * @return List<T>  --> the query result list
     */
    List<T> hqlQueryList(String hql, Map<Integer,Object> params);

    /**
     * sql查询
     * @param sql sql语句
     * @param params key: position, value: real-value
     * @return T --> the query object
     */
    T sqlQuery(String sql, Map<Integer,Object> params);

    /**
     * sql查询
     * @param sql sql语句
     * @param params key: position, value: real-value
     * @return List<T> --> the query results
     */
    List<T> sqlQueryList(String sql, Map<Integer,Object> params);

    /**
     * 根据主键id查询对象
     * @param pid 主键id
     * @return T 数据实体对象
     */
    T findByPrimaryKey(String pid) ;

    /**
     * 根据主键id删除对象
     * @param pid 主键id
     */
    void deleteByPrimaryKey(String pid);

    /**
     * 根据主键id更新对象
     * 如果对象属性为null,则会被更新为null
     * @param t 更新对象
     */
    void updateByPrimaryKey(T t);

    /**
     * 根据主键id更新对象
     * 如果有空值则不更新
     * @param t 更新对象
     */
    void mergeByPrimaryKey(T t);

    /**
     * 分页查询
     * @param pager 分页查询条件
     * @return 分页查询结果
     */
    List<T> page(Pager pager);

    Session getCurrentSession();

    SessionFactory getSessionFactory();
}
