package com.abba.controller;

import com.abba.entity.response.BaseResponse;
import com.abba.model.bo.CityFeatureParam;
import com.abba.model.bo.CityParam;
import com.abba.model.dto.CityFeatureDTO;
import com.abba.model.vo.CityFeatureVO;
import com.abba.service.ICityFeatureService;
import com.abba.util.StringHelper;
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
@RequestMapping("/cityFeature")
public class CityFeatureController {

    private final ICityFeatureService<CityFeatureDTO> cityFeatureService;

    public CityFeatureController(ICityFeatureService<CityFeatureDTO> cityFeatureService) {
        this.cityFeatureService = cityFeatureService;
    }

    /**
     * 根据城市代码获取城市特征(地标,交通枢纽 etc.)
     * @param param 城市代码
     * @return 统一返回格式 {@link BaseResponse};
     */
    @PostMapping("/cityCode")
    public BaseResponse<List<CityFeatureVO>> getByCityCode(@RequestBody CityParam param){
        List<CityFeatureDTO> dtos = cityFeatureService.getByCityCode(param);
        List<CityFeatureVO> vos = dtos.stream().map(CityFeatureVO::new).collect(Collectors.toList());
        return BaseResponse.<List<CityFeatureVO>>builder().build().success(vos);
    }

    /**
     * 根据城市代码以及片区代码获取片区特征
     * @param param 城市代码 片区代码
     * @return 统一返回格式 {@link BaseResponse};
     */
    @PostMapping("/cityCode/regionCode")
    public BaseResponse<List<CityFeatureVO>> getByRegionCode(@RequestBody CityParam param){
        List<CityFeatureDTO> dtos = cityFeatureService.getByCityCodeAndRegionCode(param);
        List<CityFeatureVO> vos = dtos.stream().map(CityFeatureVO::new).collect(Collectors.toList());
        return BaseResponse.<List<CityFeatureVO>>builder().build().success(vos);
    }

    /**
     * 根据城市代码以及行政区代码获取片区特征
     * @param param 城市代码 行政区代码
     * @return 统一返回格式 {@link BaseResponse};
     */
    @PostMapping("/cityCode/districtCode")
    public BaseResponse<List<CityFeatureVO>> getByDistrictCode(@RequestBody CityParam param){
        List<CityFeatureDTO> dtos = cityFeatureService.getByCityCodeAndDistrictCode(param);
        List<CityFeatureVO> vos = dtos.stream().map(CityFeatureVO::new).collect(Collectors.toList());
        return BaseResponse.<List<CityFeatureVO>>builder().build().success(vos);
    }

    /**
     * 添加一个特征
     * @param param 城市特征参数
     * @return 统一返回格式 {@link BaseResponse};
     */
    @PostMapping("/add")
    public BaseResponse<String> addCityFeature(@RequestBody CityFeatureParam param){
        CityFeatureDTO dto = cityFeatureService.add(param).orElseGet(CityFeatureDTO::new);
        return BaseResponse.<String>builder().build().adaptive(StringHelper::isNotEmpty,dto.getId());
    }
}
