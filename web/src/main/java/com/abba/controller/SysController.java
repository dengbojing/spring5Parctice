package com.abba.controller;

import com.abba.entity.response.BaseResponse;
import com.abba.model.bo.SysParam;
import com.abba.model.dto.SysParameterDTO;
import com.abba.model.vo.SysParameterVO;
import com.abba.service.ISysParameterService;
import com.abba.util.StringHelper;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dengbojing
 */
@RestController
@RequestMapping("/sys")
@Slf4j
public class SysController {

    @Resource
    private ObjectMapper objectMapper;

    private final ISysParameterService<SysParameterDTO> sysParameterService;

    public SysController(ISysParameterService<SysParameterDTO> sysParameterService) {
        this.sysParameterService = sysParameterService;
    }

    /**
     * 根据类型查询所有配置参数
     * @param type 类型
     * @return 统一返回格式 {@link BaseResponse}
     */
    @GetMapping("/{type}/all")
    public BaseResponse<List<SysParameterVO>> getAll(@PathVariable("type") String type){
        List<SysParameterVO> vos = new ArrayList<>();
        sysParameterService.getAll(type).forEach(sysParameterDTO -> {
            try {
                SysParameterVO vo = objectMapper.readValue(JSON.toJSONString(sysParameterDTO), SysParameterVO.class);
                vos.add(vo);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        });
        return BaseResponse.<List<SysParameterVO>>builder().build().success("成功",vos);
    }

    /**
     * 添加系统参数
     * @param param 系统参数
     * @return 统一返回格式 {@link BaseResponse}
     */
    @PostMapping("/add")
    public BaseResponse<SysParameterVO> add(@RequestBody SysParam param){
        SysParameterDTO dto = sysParameterService.add(param).orElseGet(SysParameterDTO::new);
        return BaseResponse.<SysParameterVO>builder().build().adaptive(vo -> StringHelper.isNotEmpty(vo.getId()), new SysParameterVO(dto));
    }

    /**
     * 批量添加系统参数
     * @param params
     * @return 统一返回格式 {@link BaseResponse}
     */
    @PostMapping("/batch/add")
    public BaseResponse<String> add(@RequestBody List<SysParam> params){
        sysParameterService.batchAdd(params);
        return BaseResponse.<String>builder().build().success("成功", "");
    }


}
