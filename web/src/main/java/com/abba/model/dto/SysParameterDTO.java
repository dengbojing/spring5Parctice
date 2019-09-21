package com.abba.model.dto;

import com.abba.entity.AbstractDTO;
import com.abba.model.po.SysParameter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dengbojing
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class SysParameterDTO extends AbstractDTO<SysParameter>{
    public SysParameterDTO(SysParameter sysParameter, String... ignoreProperties) {
        super(sysParameter, ignoreProperties);
    }

    private String id;

    private String parentId;

    private String value;

    private String comment;

    private String name;

    private Integer level;

    private List<SysParameterDTO> children;

    public void addChild(SysParameterDTO dto){
        if(CollectionUtils.isEmpty(children)){
            children = new ArrayList<>();
        }
        children.add(dto);
    }
}
