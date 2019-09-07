package com.abba.model.vo;

import com.abba.entity.AbstractVO;
import com.abba.model.dto.PermissionDTO;
import com.abba.model.po.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dengbojing
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PermissionVO extends AbstractVO<PermissionDTO> {

    private String id;

    private String permissionType;

    private List<PageVO> roles;

    private List<MenuVO> menus;

    public PermissionVO(PermissionDTO permission, String... ignoreProperties) {
        super(permission, ignoreProperties);
    }
}
