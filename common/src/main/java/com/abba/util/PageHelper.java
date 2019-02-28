package com.abba.util;

import com.abba.entity.vo.BaseVO;
import com.abba.entity.vo.Pager;

public interface PageHelper<T extends BaseVO> {
    Pager<T> doPage();

}
