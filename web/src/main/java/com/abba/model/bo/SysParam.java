package com.abba.model.bo;

import com.abba.entity.AbstractParam;
import lombok.Data;

/**
 * @author dengbojing
 */
@Data
public class SysParam extends AbstractParam {
    
    private String id;

    private String parentId;

    private String value;

    private String comment;

    private String name;

    private Integer level;

    private String type;

    private String code;

}
