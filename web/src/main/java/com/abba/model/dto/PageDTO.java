package com.abba.model.dto;

import com.abba.entity.AbstractDTO;
import com.abba.model.po.Page;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author dengbojing
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class PageDTO extends AbstractDTO<Page> {

    private String id;

    private String elementId;
}
