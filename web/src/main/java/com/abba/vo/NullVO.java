package com.abba.vo;

import com.abba.entity.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author dengbojing
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NullVO extends BaseVO {
    public NullVO(Object o, String... ignoreProperties) {
        super(o, ignoreProperties);
    }
}
