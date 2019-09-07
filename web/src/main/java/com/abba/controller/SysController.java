package com.abba.controller;

import com.abba.entity.response.BaseResponse;
import com.abba.model.dto.SysParameterDTO;
import com.abba.service.ISysParameterService;
import com.abba.model.vo.SysParameterVO;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * @return
     */
    @GetMapping("/{type}/all")
    public BaseResponse<List<SysParameterVO>> queryAll(@PathVariable("type") String type){
        List<SysParameterVO> vos = new ArrayList<>();
        sysParameterService.queryAll(type).forEach(sysParameterDTO -> {
            try {
                SysParameterVO vo = objectMapper.readValue(JSON.toJSONString(sysParameterDTO), SysParameterVO.class);
                vos.add(vo);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        });
        return BaseResponse.<List<SysParameterVO>>builder().build().success("成功",vos);
    }
}
