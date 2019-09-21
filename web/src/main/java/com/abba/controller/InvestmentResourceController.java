package com.abba.controller;

import com.abba.entity.request.Pager;
import com.abba.entity.response.BaseResponse;
import com.abba.model.bo.PageParam;
import com.abba.model.dto.InvestmentResourceDTO;
import com.abba.model.vo.InvestmentResourceVO;
import com.abba.service.InvestmentResourceService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dengbojing
 */
@RestController
@RequestMapping("/investmentResource")
public class InvestmentResourceController {

    private final InvestmentResourceService<InvestmentResourceDTO> investmentResourceService;

    public InvestmentResourceController(InvestmentResourceService<InvestmentResourceDTO> investmentResourceService) {
        this.investmentResourceService = investmentResourceService;
    }


    @PostMapping("/park/{parkId}")
    public BaseResponse<List<InvestmentResourceVO>>  getByParkId(@PathVariable String parkId, @RequestBody Pager pageParam){
        List<InvestmentResourceDTO> dtos = investmentResourceService.getByParkId(parkId,pageParam).getData();
        List<InvestmentResourceVO> vos = dtos.stream().map(investmentResourceDTO -> new InvestmentResourceVO().fullCopy(investmentResourceDTO,InvestmentResourceVO.class)).collect(Collectors.toList());
        return BaseResponse.<List<InvestmentResourceVO>>builder().build().adaptive(CollectionUtils::isNotEmpty,vos);
    }
}
