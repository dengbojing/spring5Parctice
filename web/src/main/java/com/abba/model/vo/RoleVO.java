package com.abba.model.vo;

import com.abba.entity.AbstractVO;
import com.abba.model.dto.RoleDTO;
import com.abba.model.po.Role;
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
public class RoleVO extends AbstractVO<RoleDTO> {

    private String id;

    private String name;

    private String type;

    private Integer status;

    private List<PermissionVO> permissions;

    public RoleVO(RoleDTO role, String... ignoreProperties) {
        super(role, ignoreProperties);
    }
}
