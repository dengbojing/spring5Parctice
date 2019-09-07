package com.abba.model.vo;

import com.abba.entity.AbstractVO;
import com.abba.model.dto.SysParameterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dengbojing
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SysParameterVO extends AbstractVO<SysParameterDTO> {
    public SysParameterVO(SysParameterDTO sysParameter,String...ignoreProperties) {
        super(sysParameter,ignoreProperties);
    }
    private String id;

    private String parentId;

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
