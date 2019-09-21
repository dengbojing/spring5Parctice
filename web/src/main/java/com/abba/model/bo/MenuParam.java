package com.abba.model.bo;

import com.abba.entity.AbstractParam;
import lombok.Data;

/**
 * @author dengbojing
 */
@Data
public class MenuParam extends AbstractParam {

    private String id;

    private String name;

    private String url;

    private Integer level;

    private Integer parentId;

    private String icon;

    private Integer order;
}
