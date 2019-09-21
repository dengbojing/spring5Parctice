package com.abba.controller;

import com.abba.entity.response.BaseResponse;
import com.abba.model.bo.CityParam;
import com.abba.model.dto.IndustrialParkDTO;
import com.abba.model.vo.IndustrialParkVO;
import com.abba.service.IndustrialParkService;
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
@RequestMapping("/industrialPark")
public class IndustrialParkController {

    private final IndustrialParkService<IndustrialParkDTO> industrialParkService;

    public IndustrialParkController(IndustrialParkService<IndustrialParkDTO> industrialParkService) {
        this.industrialParkService = industrialParkService;
    }


    /**
     * 根据城市代码获取产业园区
     * @param cityParam 城市代码
     * @return
     */
    @PostMapping("/city")
    public BaseResponse<List<IndustrialParkVO>> getParkByCity(@RequestBody CityParam cityParam){
        List<IndustrialParkDTO> dtos = industrialParkService.getParkByCity(cityParam);
        List<IndustrialParkVO> vos = dtos.stream().map(IndustrialParkVO::new).collect(Collectors.toList());
        return BaseResponse.<List<IndustrialParkVO>>builder().build().success(vos);
    }


    /**
     * 根据id获取产业园信息
     * @param id 产业园id
     * @return
     */
    @PostMapping("/{id}")
    public BaseResponse<IndustrialParkVO> getById(@PathVariable String id){
        IndustrialParkDTO dto = industrialParkService.getById(id).orElseGet(IndustrialParkDTO::new);
        return BaseResponse.<IndustrialParkVO>builder().build().success(new IndustrialParkVO(dto));
    }
}
