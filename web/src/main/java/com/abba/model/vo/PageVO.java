package com.abba.model.vo;

import com.abba.entity.AbstractVO;
import com.abba.model.dto.PageDTO;
import com.abba.model.dto.PermissionDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dengbojing
 */
@Data
@NoArgsConstructor
public class PageVO extends AbstractVO<PageDTO> {

    public PageVO(PageDTO permission, String... ignoreProperties) {
        super(permission, ignoreProperties);
    }

    private String id;

    private String elementId;
}
