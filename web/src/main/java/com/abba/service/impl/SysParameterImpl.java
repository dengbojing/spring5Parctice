package com.abba.service.impl;

import com.abba.dao.ISysParameterDao;
import com.abba.model.dto.SysParameterDTO;
import com.abba.model.po.SysParameter;
import com.abba.service.ISysParameterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author dengbojing
 */
@Service
@Slf4j
public class SysParameterImpl implements ISysParameterService<SysParameterDTO> {

    private final ISysParameterDao<SysParameter> sysParameterDao;

    public SysParameterImpl(ISysParameterDao<SysParameter> sysParameterDao) {
        this.sysParameterDao = sysParameterDao;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public List<SysParameterDTO> queryByParentIdAndType(Integer id, String type) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Optional<SysParameterDTO> queryRootByType(String type) {
        Map<Integer,Object> params = new HashMap<>(2);
        params.put(1, type);
        SysParameter parameter = sysParameterDao.hqlQuery(" from SysParameter where type = ?1 and level = 0",params);
        return Optional.of(new SysParameterDTO(parameter));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public List<SysParameterDTO> queryAll(String type) {
        Map<Integer,Object> params = new HashMap<>(2);
        params.put(1, type);
        List<SysParameter> list = sysParameterDao.hqlQueryList("from SysParameter where type = ?1",params);
        List<SysParameterDTO> voList = list.stream().map(sysParameter ->  new SysParameterDTO(sysParameter)).collect(Collectors.toList());
        for(int i = 0; i < voList.size(); i++){
            SysParameterDTO vo1 = voList.get(i);
            for(int j = 0; j < voList.size(); j++){
                SysParameterDTO vo2 = voList.get(j);
                if(vo2.getParentId().equals(vo1.getId())){
                    vo1.addChild(vo2);
                }
            }
        }
        Map<Integer, List<SysParameterDTO>> map = voList.stream().collect(Collectors.groupingBy(SysParameterDTO::getLevel,TreeMap::new,Collectors.toList()));
        return map.get(0);
    }
}
