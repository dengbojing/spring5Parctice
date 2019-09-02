package com.abba.vo;

import com.abba.entity.vo.BaseVO;
import com.abba.model.SysParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dengbojing
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysParameterVO extends BaseVO<SysParameter> {
    public SysParameterVO(SysParameter sysParameter,String...ignoreProperties) {
        super(sysParameter,ignoreProperties);
    }
    private Integer id;

    private Integer parentId;

    private String value;

    private String comment;

    private String name;

    private Integer level;

    private List<SysParameterVO> children;

    public void addChild(SysParameterVO vo){
        if(CollectionUtils.isEmpty(children)){
            children = new ArrayList<>();
        }
        children.add(vo);
    }
}
