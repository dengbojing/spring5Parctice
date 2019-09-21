package com.abba.model.dto;

import com.abba.entity.AbstractDTO;
import com.abba.model.po.Permission;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author dengbojing
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class PermissionDTO extends AbstractDTO<Permission>{
    public PermissionDTO(Permission permission, String... ignoreProperties) {
        super(permission, ignoreProperties);
    }

    private String id;

    private String permissionType;

    private String permissionName;

    private List<MenuDTO> menus;

    private List<PageDTO> pages;
}
