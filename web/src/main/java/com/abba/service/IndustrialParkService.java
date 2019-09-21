package com.abba.service;

import com.abba.model.bo.CityParam;

import java.util.List;
import java.util.Optional;

/**
 * @author dengbojing
 */
public interface IndustrialParkService<T> {

    /**
     * 根据城市代码获取所有产业园区
     * @param cityParam 城市代码
     * @return
     */
    List<T> getParkByCity(CityParam cityParam);

    /**
     * 根据id获取产业详情
     * @param id 产业id
     * @return
     */
    Optional<T> getById(String id);
}
