package com.abba.dao.base;

import com.abba.entity.request.Pager;
import com.abba.util.ObjectHelper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import static com.abba.util.SqlHelper.requireWhere;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author dengbojing
 * @param <T>
 */
@Slf4j
public abstract class AbstractHibernateDao<T extends Serializable> implements IBaseDao<T>{

    protected Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    protected AbstractHibernateDao(){
        this.clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 创建对象
     * @param entity 实体对象
     */
    @Override
    public void create(T entity) {
        checkNotNull(entity);
        getCurrentSession().persist(entity);
    }

    /**
     * 创建对象
     * @param entity 实体对象
     */
    @Override
    public void insert(T entity){
        this.create(entity);
    }

    /**
     * hql查询
     * @param hql hql语句
     * @param params key: position, value: real-value
     * @return T --> the query result
     */
    @Override
    public T hqlQuery(String hql, Map<Integer,Object> params){
        checkNotNull(hql);
        return queryEntity(hql, params).uniqueResult();
    }

    /**
     * hql查询
     * @param hql hql语句
     * @param params key: position, value: real-value
     * @return List<T>  --> the query result list
     */
    @Override
    public List<T> hqlQueryList(String hql, Map<Integer,Object> params){
        checkNotNull(hql);
        requireWhere(hql);
        return queryEntity(hql, params).list();
    }

    /**
     * sql查询
     * @param sql sql语句
     * @param params key: position, value: real-value
     * @return T --> the query object
     */
    @Override
    public T sqlQuery(String sql, Map<Integer,Object> params){
        checkNotNull(sql);
        return nativeQueryEntity(sql, params).uniqueResult();
    }

    /**
     * sql查询
     * @param sql sql语句
     * @param params key: position, value: real-value
     * @return List<T> --> the query results
     */
    @Override
    public List<T> sqlQueryList(String sql, Map<Integer,Object> params){
        checkNotNull(sql);
        requireWhere(sql);
        return nativeQueryEntity(sql,params).list();
    }

    /**
     * 根据主键id查询对象
     * @param pid 主键id
     * @return T 数据实体对象
     */
    @Override
    public T findByPrimaryKey(String pid) {
        checkNotNull(pid);
        return getCurrentSession().find(clazz, pid);
    }

    /**
     * 根据主键id删除对象
     * @param pid 主键id
     */
    @Override
    public void deleteByPrimaryKey(String pid){
        T t = findByPrimaryKey(pid);
        getCurrentSession().delete(t);
    }


    /**
     * 根据主键id更新对象
     * 如果对象属性为null,则会被更新为null
     * @param t 更新对象
     */
    @Override
    public void updateByPrimaryKey(T t){
        checkNotNull(t);
        getCurrentSession().update(t);
    }

    /**
     * 根据主键id更新对象
     * 如果有空值则不更新
     * @param t 更新对象
     */
    @Override
    public void mergeByPrimaryKey(T t){
        checkNotNull(t);
        getCurrentSession().merge(t);
    }

    /**
     * 分页查询
     * @param pager 分页查询条件
     * @return 分页集合
     */
    @Override
    public List<T> page(Pager pager) {
        CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(this.clazz);
        Root<T> root = query.from(this.clazz);
        query.select(root);
        TypedQuery<T> typedQuery=entityManager.createQuery(query);
        typedQuery.setFirstResult(pager.getPageNum()*pager.getPageSize());
        typedQuery.setMaxResults(pager.getPageSize());
        return typedQuery.getResultList();
    }

    /**
     * 获取session
     * @return hibernate session
     */
    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     *
     * @param sql sql语句
     * @param params 参数
     * @return sql查询对象
     */
    private NativeQuery<T> nativeQueryEntity(String sql, Map<Integer, Object> params){
        NativeQuery<T> query = getCurrentSession().createNativeQuery(sql,clazz);
        if(ObjectHelper.isNotEmpty(params)){
            params.forEach((k,v) -> query.setCacheable(true).setParameter(k,v));
        }
        return query;
    }

    /**
     *
     * @param hql hql语句
     * @param params 参数
     * @return hql查询对象
     */
    private Query<T> queryEntity(String hql, Map<Integer, Object> params){
        Query<T> query = getCurrentSession().createQuery(hql,clazz);
        if(ObjectHelper.isNotEmpty(params)){
            params.forEach((k,v) -> query.setCacheable(true).setParameter(k,v));
        }
        return query;
    }

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
