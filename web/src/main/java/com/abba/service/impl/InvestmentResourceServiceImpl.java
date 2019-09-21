package com.abba.service.impl;

import com.abba.dao.InvestmentResourceDao;
import com.abba.entity.request.Pager;
import com.abba.model.bo.PageParam;
import com.abba.model.dto.InvestmentResourceDTO;
import com.abba.model.po.InvestmentResource;
import com.abba.service.InvestmentResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dengbojing
 */
@Service
public class InvestmentResourceServiceImpl implements InvestmentResourceService<InvestmentResourceDTO> {

    private final InvestmentResourceDao<InvestmentResource> resourceDao;

    public InvestmentResourceServiceImpl(InvestmentResourceDao<InvestmentResource> resourceDao) {
        this.resourceDao = resourceDao;
    }

    @Override
    public Pager<InvestmentResourceDTO> getByParkId(String parkId, Pager pager) {
        resourceDao.page(pager,"from InvestmentResource");
        return null;
    }
}
