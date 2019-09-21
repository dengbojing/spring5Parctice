package com.abba.model.bo;

import com.abba.entity.AbstractParam;
import lombok.Data;

import java.util.List;

/**
 * @author dengbojing
 */
@Data
public class PermissionParam extends AbstractParam {

    private String id;

    private String permissionType;

    private String permissionName;

    private List<MenuParam> menus;

    private List<PageParam> pages;
}
