package com.abba.dao;

import com.abba.util.ObjectJudge;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * @author dengbojing
 * @param <T>
 */
@Slf4j
public abstract class AbstractHibernateDao<T extends Serializable> {

    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    protected AbstractHibernateDao(){
        this.clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 创建对象
     * @param entity 实体对象
     */
    public void create(T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().persist(entity);
    }

    /**
     * hql查询
     * @param hql hql语句
     * @param params key: position, value: real-value
     * @return T --> the query object
     */
    public T hqlQuery(String hql, Map<Integer,Object> params){
        Preconditions.checkNotNull(hql);
        Query<T> query = getCurrentSession().createQuery(hql,clazz);
        if(ObjectJudge.isNotEmpty(params)){
            params.forEach((k,v) -> query.setCacheable(true).setParameter(k,v));
        }
        return query.uniqueResult();

    }

    /**
     * sql查询
     * @param sql sql语句
     * @param params key: position, value: real-value
     * @return T --> the query object
     */
    public T sqlQuery(String sql, Map<Integer,Object> params){
        Preconditions.checkNotNull(sql);
        NativeQuery<T> query = getCurrentSession().createSQLQuery(sql);
        if(ObjectJudge.isNotEmpty(params)){
            params.forEach((k,v) -> query.setCacheable(true).setParameter(k,v));
        }
        return query.uniqueResult();
    }

    /**
     * 根据主键id查询对象
     * @param pid 主键id
     * @return T
     */
    public T findEntity(String pid) {
        Preconditions.checkNotNull(pid);
        return getCurrentSession().find(clazz, pid);
    }

    /**
     * 根据主键id删除对象
     * @param pid 主键id
     */
    public void deleteEntity(String pid){
        T t = findEntity(pid);
        getCurrentSession().delete(t);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
