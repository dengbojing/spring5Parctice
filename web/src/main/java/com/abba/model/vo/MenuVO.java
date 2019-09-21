package com.abba.model.vo;

import com.abba.entity.AbstractVO;
import com.abba.model.dto.MenuDTO;
import com.abba.model.po.Menu;
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
public class MenuVO extends AbstractVO<MenuDTO> {

    private String id;

    private String name;

    private String url;

    private Integer level;

    private Integer parentId;

    private String icon;

    private Integer order;

    public MenuVO(MenuDTO dto, String... ignoreProperties) {
        super(dto, ignoreProperties);
    }
}
