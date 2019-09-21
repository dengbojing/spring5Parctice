package com.abba.service.impl;

import com.abba.dao.IndustrialParkDao;
import com.abba.model.bo.CityParam;
import com.abba.model.dto.IndustrialParkDTO;
import com.abba.model.po.IndustrialPark;
import com.abba.service.IndustrialParkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author dengbojing
 */
@Service
public class IndustrialParkServiceImpl implements IndustrialParkService<IndustrialParkDTO> {

    private final IndustrialParkDao<IndustrialPark> industrialParkDao;

    public IndustrialParkServiceImpl(IndustrialParkDao<IndustrialPark> industrialParkDao) {
        this.industrialParkDao = industrialParkDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<IndustrialParkDTO> getParkByCity(CityParam cityParam) {
        String hql = " from IndustrialPark where cityCode = ?0";
        Map<Integer, Object> paramMap = new HashMap<>(2);
        paramMap.put(0, cityParam.getCityCode());
        List<IndustrialPark> industrialParks = industrialParkDao.hqlQueryList(hql, paramMap);
        return industrialParks.stream().map(IndustrialParkDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Optional<IndustrialParkDTO> getById(String id) {
        return Optional.of(new IndustrialParkDTO(industrialParkDao.findByPrimaryKey(id)));
    }
}
