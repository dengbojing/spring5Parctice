package com.abba.model.dto;

import com.abba.entity.AbstractDTO;
import com.abba.model.po.Menu;
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
public class MenuDTO extends AbstractDTO<Menu>{

    public MenuDTO(Menu menu, String... ignoreProperties) {
        super(menu, ignoreProperties);
    }

    private String id;

    private String name;

    private String url;

    private Integer level;

    private Integer parentId;

    private String icon;

    private Integer order;

}
