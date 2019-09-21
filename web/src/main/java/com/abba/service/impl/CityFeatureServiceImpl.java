package com.abba.service.impl;

import com.abba.dao.ICityFeatureDao;
import com.abba.model.bo.CityFeatureParam;
import com.abba.model.bo.CityParam;
import com.abba.model.dto.CityFeatureDTO;
import com.abba.model.po.CityFeature;
import com.abba.service.ICityFeatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author dengbojing
 */
@Slf4j
@Service
public class CityFeatureServiceImpl implements ICityFeatureService<CityFeatureDTO> {

    private final ICityFeatureDao<CityFeature> cityFeatureDao;

    public CityFeatureServiceImpl(ICityFeatureDao<CityFeature> cityFeatureDao) {
        this.cityFeatureDao = cityFeatureDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<CityFeatureDTO> getByCityCode(CityParam param) {
        String hql = "from CityFeature where city.cityCode=?0";
        Map<Integer,Object> paramMap = new HashMap<>(2);
        paramMap.put(0,param.getCityCode());
        List<CityFeature> features = cityFeatureDao.hqlQueryList(hql, paramMap);
        return features.stream().map(cityFeature -> new CityFeatureDTO().fullCopy(cityFeature,CityFeatureDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<CityFeatureDTO> getByCityCodeAndRegionCode(CityParam param) {
        String hql = "from CityFeature where city.cityCode=?0 and city.regionCode = ?!";
        Map<Integer,Object> paramMap = new HashMap<>(2);
        paramMap.put(0,param.getCityCode());
        paramMap.put(1,param.getRegionCode());
        List<CityFeature> features = cityFeatureDao.hqlQueryList(hql, paramMap);
        return features.stream().map(cityFeature -> new CityFeatureDTO().fullCopy(cityFeature,CityFeatureDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<CityFeatureDTO> getByCityCodeAndDistrictCode(CityParam param) {
        String hql = "from CityFeature where city.cityCode=?0 and city.districtCode = ?!";
        Map<Integer,Object> paramMap = new HashMap<>(2);
        paramMap.put(0,param.getCityCode());
        paramMap.put(1,param.getDistrictCode());
        List<CityFeature> features = cityFeatureDao.hqlQueryList(hql, paramMap);
        return features.stream().map(cityFeature -> new CityFeatureDTO().fullCopy(cityFeature,CityFeatureDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Optional<CityFeatureDTO> add(CityFeatureParam param) {
        CityFeature cityFeature = new CityFeature();
        try {
            cityFeature = param.copyTo(param,CityFeature.class);
            cityFeatureDao.create(cityFeature);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return Optional.of(new CityFeatureDTO().fullCopy(cityFeature,CityFeatureDTO.class));
    }
}
