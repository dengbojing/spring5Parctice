package com.abba.controller;

import com.abba.entity.BaseResponse;
import com.abba.model.SysParameter;
import com.abba.service.ISysParameterService;
import com.abba.vo.SysParameterVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dengbojing
 */
@RestController
@RequestMapping("/sys")
public class SysController {

    private final ISysParameterService<SysParameter> sysParameterService;

    public SysController(ISysParameterService<SysParameter> sysParameterService) {
        this.sysParameterService = sysParameterService;
    }


    @GetMapping("/{type}/all")
    public BaseResponse<List<SysParameterVO>> queryAll(@PathVariable("type") String type){
        return BaseResponse.<List<SysParameterVO>>builder().build().success("成功",sysParameterService.queryAll(type));
    }
}
