package com.abba.service;

import com.abba.model.bo.CityFeatureParam;
import com.abba.model.bo.CityParam;

import java.util.List;
import java.util.Optional;

/**
 * @author dengbojing
 */
public interface ICityFeatureService<T> {

    /**
     * 根据城市代码获取城市特征
     * @param param 城市代码
     * @return 城市特征集合
     */
    List<T> getByCityCode(CityParam param);

    /**
     * 根据城市和片区代码获取城市特征
     * @param param 城市代码,片区代码
     * @return 城市特征集合
     */
    List<T> getByCityCodeAndRegionCode(CityParam param);

    /**
     * 根据城市代码和行政区代码获取城市特征
     * @param param 成代码,行政区代码
     * @return 城市特征集合
     */
    List<T> getByCityCodeAndDistrictCode(CityParam param);

    /**
     * 添加城市特征
     * @param param 城市特征参数
     * @return
     */
    Optional<T> add(CityFeatureParam param);
}
