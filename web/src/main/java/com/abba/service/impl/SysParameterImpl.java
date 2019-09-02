package com.abba.service.impl;

import com.abba.dao.ISysParameterDao;
import com.abba.model.SysParameter;
import com.abba.service.ISysParameterService;
import com.abba.vo.SysParameterVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dengbojing
 */
@Service
@Slf4j
public class SysParameterImpl implements ISysParameterService<SysParameter> {

    private final ISysParameterDao<SysParameter> sysParameterDao;

    public SysParameterImpl(ISysParameterDao<SysParameter> sysParameterDao) {
        this.sysParameterDao = sysParameterDao;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public List<SysParameter> queryByParentIdAndType(Integer id, String type) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Optional<SysParameter> queryRootByType(String type) {
        Map<Integer,Object> params = new HashMap<>(2);
        params.put(1, type);
        return Optional.of(sysParameterDao.hqlQuery(" from SysParameter where type = ?1 and level = 0",params));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public List<SysParameterVO> queryAll(String type) {
        Map<Integer,Object> params = new HashMap<>(2);
        params.put(1, type);
        List<SysParameter> list = sysParameterDao.hqlQueryList("from SysParameter where type = ?1",params);
        List<SysParameterVO> voList = list.stream().map(sysParameter -> new SysParameterVO(sysParameter)).collect(Collectors.toList());
        for(int i = 0; i < voList.size(); i++){
            SysParameterVO vo1 = voList.get(i);
            for(int j = 0; j < voList.size(); j++){
                SysParameterVO vo2 = voList.get(j);
                if(vo2.getParentId().equals(vo1.getId())){
                    vo1.addChild(vo2);
                }
            }
        }
        Map<Integer, List<SysParameterVO>> map = voList.stream().collect(Collectors.groupingBy(SysParameterVO::getLevel,TreeMap::new,Collectors.toList()));
        return map.get(0);
    }
}
