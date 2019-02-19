package com.abba.util;

import com.abba.entity.vo.BaseVO;

public interface VOHelper<S,T extends BaseVO> {
    T copyPropertties(S s);

}
