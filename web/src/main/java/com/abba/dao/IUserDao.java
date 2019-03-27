package com.abba.dao;

import com.abba.dao.base.IBaseDao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author dengbojing
 */

public interface IUserDao<T extends Serializable> extends IBaseDao<T> {

    void update(T t);

    void updateExceptNull(T t);

}
