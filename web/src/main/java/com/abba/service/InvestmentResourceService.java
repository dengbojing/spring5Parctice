package com.abba.service;

import com.abba.entity.request.Pager;
import com.abba.model.bo.PageParam;

import java.util.List;

/**
 * @author dengbojing
 */
public interface InvestmentResourceService<T> {

    /**
     * 根据产业园id 分页查询产业园所属招商资源
     * @param parkId 产业园id
     * @param pageParam 分页参数
     * @return 招商资源集合
     */
    Pager<T> getByParkId(String parkId, Pager pageParam);
}
