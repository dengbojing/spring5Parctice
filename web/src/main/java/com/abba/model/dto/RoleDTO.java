package com.abba.model.dto;

import com.abba.entity.AbstractDTO;
import com.abba.model.po.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class RoleDTO extends AbstractDTO<Role>{
    public RoleDTO(Role role, String... ignoreProperties) {
        super(role, ignoreProperties);
    }

    private String id;

    private String name;

    private String type;

    private Integer status;

    private List<PermissionDTO> permissions;
}
