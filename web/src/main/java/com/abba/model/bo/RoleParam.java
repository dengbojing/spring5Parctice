package com.abba.model.bo;

import com.abba.entity.AbstractParam;
import lombok.Data;

import java.util.List;

/**
 * @author dengbojing
 */
@Data
public class RoleParam extends AbstractParam {

    private String id;

    private String name;

    private String type;

    private Integer status;

    private List<PermissionParam> permissions;
}
